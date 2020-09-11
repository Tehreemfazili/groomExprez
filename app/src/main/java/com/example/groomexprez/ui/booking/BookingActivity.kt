package com.example.groomexprez.ui.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.groomexprez.common.BaseActivity
import com.example.groomexprez.databinding.ActivityBookingBinding

class BookingActivity : BaseActivity(){

    private val binding : ActivityBookingBinding by lazy{
        ActivityBookingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter =
            SwipeAdapter(
                supportFragmentManager,
                fragmentList()
            )

    }

    class SwipeAdapter(
        fm: FragmentManager, private var items: ArrayList<Fragment>
    ) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = items[position]

        override fun getCount() = items.size

    }

    private fun fragmentList() = arrayListOf(
        BookingTimeFragment(),
        BookingFormFragment()

    )
}