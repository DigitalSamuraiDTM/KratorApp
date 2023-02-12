package com.digitalsamurai.kratorapp

import android.util.Log
import androidx.arch.core.executor.TaskExecutor
import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.automator.queue.TaskQueue
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference

class TaskManager(private val uiDevice: UiDevice) {

    private var taskQueue = TaskQueue()

    private var currentTask = AtomicReference<Task?>()

    init {
        startTaskPipeline()
    }

    fun acceptTaskType(taskType : TaskType){
        //can smart checking if need before add in queue
        taskQueue.addTask(taskType)
    }


    fun startTaskPipeline() = CoroutineScope(Dispatchers.IO).launch {
        while (true){
            val currentTask = waitTask()

            currentTask.startExecution(uiDevice)
                .cancellable().collect{
                //trigger task execution

            }


            finishCurrentTask()

        }
    }


    private suspend fun waitTask() : Task{
        if (currentTask.get()!=null){
            return currentTask.get()!!
        }
        var queueTask = taskQueue.nextTask()
        while (queueTask== null){
            delay(500)
            queueTask = taskQueue.nextTask()
        }
        currentTask.set(queueTask)
        return queueTask
    }
    private suspend fun finishCurrentTask(){
        currentTask.set(null)
    }
}