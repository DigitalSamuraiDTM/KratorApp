package app

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.TaskManager
import com.digitalsamurai.kratorapp.task.TaskType
import com.digitalsamurai.kratorapp.task.concrete.TaskOpenApp
import com.digitalsamurai.kratorapp.task.concrete.TaskOpenNotification
import com.digitalsamurai.kratorapp.task.concrete.TaskPressHome
import com.digitalsamurai.kratorapp.task.concrete.TaskSwipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskManagerTest {
    lateinit var taskManager : TaskManager

    var context : Context? = null
    @Before
    fun before(){
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        taskManager = TaskManager(uiDevice)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test() = runBlocking {

        val homeTask = TaskType.ReactiveTask(TaskPressHome(1000L,1000L,"home"))
        val taskNotif = TaskType.ReactiveTask(TaskOpenNotification(0L,1000L,"home"))
        val taskSwipe = TaskType.ReactiveTask(TaskSwipe(0L,1000L,"home",1000,0,0,0,3))
        val taskOpenApp = TaskType.ReactiveTask(TaskOpenApp(context!!,"com.android.vending","openApp",1000L,1000L))
//        taskManager.acceptTaskType(taskSwipe)
//        taskManager.acceptTaskType(homeTask)
//        taskManager.acceptTaskType(taskNotif)
        taskManager.acceptTaskType(taskOpenApp)
        delay(9000000L)
    }
}