package com.digitalsamurai.kratorapp.automator.queue

import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskType
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue

class TaskQueue {


    private var taskQueue : ConcurrentLinkedQueue<Task> = ConcurrentLinkedQueue()

    private var taskSheduler : TaskSheduler = TaskSheduler()

    private var shedulingScope = CoroutineScope(Dispatchers.IO)
    private var shedulingJob : Job? = null

    init {
        initTaskSheduling()
    }

    fun nextTask() : Task? = taskQueue.poll()

    fun addTask(task : TaskType){
        //no open-closed principle sorting types :(
        when(task){
            is TaskType.LazyTask -> {taskSheduler.addLazyTask(task)}
            is TaskType.ReactiveTask -> {taskQueue.add(task.task)}
        }
    }


    private fun initTaskSheduling(){
        shedulingJob?.cancel()
        shedulingJob = shedulingScope.launch {
            taskSheduler.startSheduler().collect{
                taskQueue.add(it)
            }
        }
    }

}