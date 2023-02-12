package com.digitalsamurai.kratorapp.task.concrete

import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.exceptions.UiTaskException
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskExecutionStatus
import kotlinx.coroutines.flow.FlowCollector

class TaskPressHome( private val delayBeforeTask : Long,
                     private val delayAfterTask : Long,
                     val taskName : String) : Task( taskName, delayBeforeTask, delayAfterTask) {
    override suspend fun task(uiDevice : UiDevice,flowCollector: FlowCollector<TaskExecutionStatus>) {
        if (!uiDevice.pressHome()){
            throw UiTaskException("Task press home was not completed")
        }
    }
}
