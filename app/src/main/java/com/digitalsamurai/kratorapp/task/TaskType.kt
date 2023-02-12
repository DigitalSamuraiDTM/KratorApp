package com.digitalsamurai.kratorapp.task

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

sealed class TaskType{
    data class ReactiveTask(
        @SerializedName("task")
        val task : Task) : TaskType()
    data class LazyTask(
        @SerializedName("trigger_time")
        val triggerTime: LocalDateTime,
        @SerializedName("task")
        val task : Task) : TaskType()
}
