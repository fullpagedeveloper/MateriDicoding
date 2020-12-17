package com.fullpagedeveloper.mybackgroundthread

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import kotlin.Exception
//yang di komen sebelum coroutine
//class MainActivity : AppCompatActivity(), MyAsyncCallback {
class MainActivity : AppCompatActivity() {

    companion object {
        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
        private val LOG_ASYNC = "DemoAsync"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_status.setText(R.string.status_pre)
        tv_desc.text = INPUT_STRING

        GlobalScope.launch(Dispatchers.IO){
            //background tread
            val input = INPUT_STRING

            var output: String? = null
            Log.d(LOG_ASYNC, "status: doInBackground")

            try {
                //input stringnya ditambahkan dengan string 'Selamat Belajar!!"
                output = "$input Selamat belajar"

                delay(2000)

                //Pindah ke main thread untuk update UI
                withContext(Dispatchers.Main){
                    tv_status.setText(R.string.status_post)
                    tv_desc.text = output
                }

            } catch (e: Exception){
                Log.d(LOG_ASYNC, e.message.toString())
            }
        }

        //val demoAsync = DemoAsync(this)
        //demoAsync.execute(INPUT_STRING)
    }

    /*override fun onPreExecute() {
        tv_status.setText(R.string.status_pre)
        tv_desc.text = INPUT_STRING
    }

    override fun onPostExecute(text: String) {
        tv_status.setText(R.string.status_post)
        tv_desc.text = text
    }*/

    /*private class DemoAsync(myListener: MyAsyncCallback) : AsyncTask<String, Void, String>() {

        private val myListener: WeakReference<MyAsyncCallback>
        init {
            this.myListener = WeakReference(myListener)
        }

        companion object {
            private val LOG_ASYNC = "DemoAsync"
        }

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(LOG_ASYNC, "status : onPreExecute")

            val myListener = myListener.get()
            myListener?.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            Log.d(LOG_ASYNC, "status : doInBackground")

            var output: String? = null

            try {
                val input = params[0]
                output = "$input Selamat Belajar!!"
                Thread.sleep(2000)
            } catch (e: Exception) {
                e.message?.let { Log.d(LOG_ASYNC, it) }
            }

            return output.toString()
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.d(LOG_ASYNC, "status : onPostExecute")

            val myListener = this.myListener.get()
            myListener?.onPostExecute(result)
        }
    }*/
}

/*
internal interface MyAsyncCallback {
    fun onPreExecute()
    fun onPostExecute(text: String)
}*/
/*
internal interface MyAsyncCallback {
    fun onPreExecute()
    fun onPostExecute(text: String)
}*/
