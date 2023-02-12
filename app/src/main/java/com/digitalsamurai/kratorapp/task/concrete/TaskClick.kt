package com.digitalsamurai.kratorapp.task.concrete

import android.app.UiAutomation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.digitalsamurai.kratorapp.exceptions.UiTaskException
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskExecutionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class TaskClick( private val delayBeforeTask : Long,
                 private val delayAfterTask : Long,
                 val taskName : String,
                 val x : Int, val y : Int) : Task(taskName,delayBeforeTask, delayAfterTask) {
    override suspend fun task(uiDevice: UiDevice,flowCollector: FlowCollector<TaskExecutionStatus>) {
        if (!uiDevice.click(x,y)){
            throw UiTaskException("Task click was not completed")
        }
    }
}