package com.example.groomexprez.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.groomexprez.common.BaseActivity
import com.example.groomexprez.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity(){

    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    fun init() {
        handler = Handler()
        runnable = Runnable {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

        }
    }

    override fun onStart() {
        super.onStart()
        handler?.postDelayed(runnable ?: return, 1500)
    }
}