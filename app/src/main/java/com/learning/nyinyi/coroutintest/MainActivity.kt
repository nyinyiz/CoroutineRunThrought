package com.learning.nyinyi.coroutintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            toast("Hello I am coming!!!")
            delay(5000) // 5 seconds
            textView.text = "I have changed after 10 matibuu"

        }

    }

    private fun toast(msg: String)   {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}
