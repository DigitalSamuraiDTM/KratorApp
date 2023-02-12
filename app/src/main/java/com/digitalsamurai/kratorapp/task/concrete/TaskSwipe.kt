package com.digitalsamurai.kratorapp.task.concrete

import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.exceptions.UiTaskException
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskExecutionStatus
import kotlinx.coroutines.flow.FlowCollector

class TaskSwipe( private val delayBeforeTask : Long,
                 private val delayAfterTask : Long,
                val taskName : String,
                val xStart : Int,
                val yStart : Int,
                val xEnd : Int,
                val yEnd:Int,
                val steps : Int) : Task( taskName,delayBeforeTask, delayAfterTask) {
    override suspend fun task(uiDevice: UiDevice,flowCollector: FlowCollector<TaskExecutionStatus>) {
        if (!uiDevice.swipe(xStart,yStart,xEnd,yEnd,steps)){
            throw UiTaskException("Task swipe was not completed")
        }

    }
}