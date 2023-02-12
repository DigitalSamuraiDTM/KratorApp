package com.digitalsamurai.kratorapp.task.concrete

import android.content.Context
import android.content.Intent
import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.exceptions.UiTaskException
import com.digitalsamurai.kratorapp.task.Task
import com.digitalsamurai.kratorapp.task.TaskExecutionStatus
import kotlinx.coroutines.flow.FlowCollector

class TaskOpenApp(
    private val context : Context,
    private val packageApp: String,
    private val taskName: String,
    private val delayBeforeTask: Long,
    private val delayAfterTask: Long) : Task(taskName, delayBeforeTask, delayAfterTask
) {
    override suspend fun task(
        uiDevice: UiDevice,
        flowCollector: FlowCollector<TaskExecutionStatus>
    ) {
        val intent = context.packageManager.getLaunchIntentForPackage(packageApp)
        if (intent!=null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else{
            throw UiTaskException("Task open app was not completed")
        }
    }
}