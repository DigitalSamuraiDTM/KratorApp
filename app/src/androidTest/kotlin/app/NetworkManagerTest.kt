package app

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.digitalsamurai.kratorapp.network.NetworkManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkManagerTest {

    lateinit var networkManager: NetworkManager
    @Before
    fun before(){
        networkManager = NetworkManager(InstrumentationRegistry.getInstrumentation().context)
    }

    @Test
    fun testIpAddress() {
        val address = networkManager.getNetworkIpAddress()
        Log.d(TestConstants.LOG_TAG,address.toString())
        assert(address!="")
    }
}