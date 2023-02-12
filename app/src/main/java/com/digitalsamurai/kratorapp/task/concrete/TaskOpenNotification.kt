package com.digitalsamurai.kratorapp.task.concrete

import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.exceptions.UiTaskException
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskExecutionStatus
import kotlinx.coroutines.flow.FlowCollector

class TaskOpenNotification( private val delayBeforeTask : Long,
                            private val delayAfterTask : Long,
                            val taskName : String) : Task( taskName,delayBeforeTask, delayAfterTask) {
    override suspend fun task(uiDevice: UiDevice,flowCollector: FlowCollector<TaskExecutionStatus>) {
        if(uiDevice.openNotification()){
            throw UiTaskException("Task open notification was not completed")
        }
    }
}