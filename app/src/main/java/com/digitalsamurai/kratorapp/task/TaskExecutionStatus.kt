package com.digitalsamurai.kratorapp.task

sealed class TaskExecutionStatus(val taskName : String) {
    class Start(taskName: String) : TaskExecutionStatus(taskName)
    class InProgress(val progress : Int, val max : Int, taskName: String) : TaskExecutionStatus(taskName)
    class Finished(taskName: String) : TaskExecutionStatus(taskName)
    class Error(taskName: String,val exception: java.lang.Exception) : TaskExecutionStatus(taskName)
}