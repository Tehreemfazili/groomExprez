package com.example.groomexprez.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groomexprez.databinding.ActivityMainBinding
import com.example.groomexprez.ui.booking.BookingActivity
import kotlinx.android.synthetic.main.content_main.view.*

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.contentMain.tv_booking.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }
    }
}