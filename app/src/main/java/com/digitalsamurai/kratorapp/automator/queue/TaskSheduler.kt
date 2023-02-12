package com.digitalsamurai.kratorapp.automator.queue

import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDateTime
import java.util.concurrent.CopyOnWriteArrayList

/**
 * [TaskSheduler] is specicific class which  save every [LazyTask] in storage and release it when time comes
 * [shedulerDelay] default delay for checking time == 1min
 * If lazy task will have past trigger time compare to the system's current time ->  Sheduller release that time in first checking
 * */
class TaskSheduler(private val shedulerDelay : Long = 60*1000L) {


    private var taskArray : CopyOnWriteArrayList<TaskType.LazyTask> = CopyOnWriteArrayList()


    fun addLazyTask(lazyTask : TaskType.LazyTask){
        taskArray.add(lazyTask)
    }


    /**
     * Shedule every minute and check our sheduled task array
     * if shedule task was triggered -> remove from shedule task array and add to release it in flow
     * */
    fun startSheduler() : Flow<Task> {
        return flow {

            taskArray.forEach {
                if (it.triggerTime.isAfter(LocalDateTime.now())){
                    //trigger!
                    this.emit(it.task)
                    taskArray.remove(it)
                }


            }

            delay(shedulerDelay)
        }.flowOn(Dispatchers.IO)
    }
}