package com.digitalsamurai.kratorapp

import android.app.Instrumentation
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.test.uiautomator.UiDevice
import com.digitalsamurai.kratorapp.exceptions.UiTaskException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        }

    }
}
@Preview
@Composable()
fun mainView(){

}