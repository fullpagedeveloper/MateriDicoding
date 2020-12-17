package com.fullpagedeveloper.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fullpagedeveloper.fragment.senddatainfragment.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.apply {
            beginTransaction().add(R.id.framRoot, HomeFragment())
                .commit()
        }
    }
}