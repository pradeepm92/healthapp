package com.zeoner.healthapp.createaccount.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.zeoner.healthapp.createaccount.view.IdProofFragment
import com.zeoner.healthapp.createaccount.view.PersonalDetailFragment

class PageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    private lateinit var viewPager: ViewPager



    override fun getCount(): Int {
            return 2;
        }


        override fun getItem(position: Int): Fragment {
            when(position) {
                0 -> {
                    return PersonalDetailFragment()
                }
                1 -> {
                    return IdProofFragment()
                }

                else -> {
                    return PersonalDetailFragment()
                }
            }
        }

    fun navigateToNextFragment() {
        val currentPosition = getItemPosition(viewPager.currentItem)
        val nextPosition = currentPosition + 1

        if (nextPosition < count) {
            viewPager.setCurrentItem(nextPosition, true)
        }
    }
    }
