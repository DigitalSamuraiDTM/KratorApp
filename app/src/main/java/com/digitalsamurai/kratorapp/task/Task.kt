package com.digitalsamurai.kratorapp.task

import androidx.test.uiautomator.UiDevice
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class Task( private val taskName : String, private val delayBeforeTask : Long, private val delayAfterTask : Long) {

    abstract suspend fun task(uiDevice: UiDevice,flowCollector: FlowCollector<TaskExecutionStatus>)



    fun startExecution(uiDevice: UiDevice) : Flow<TaskExecutionStatus>{
        return flow {
            try {
                this.emit(TaskExecutionStatus.Start(taskName))
                delay(delayBeforeTask)
                task(uiDevice,this)
                delay(delayAfterTask)
                this.emit(TaskExecutionStatus.Finished(taskName))
            } catch (e : java.lang.Exception){
                this.emit(TaskExecutionStatus.Error(taskName,e))
            } finally {
                //close flow
            }
        }
    }
}