package com.fullpagedeveloper.mytablayout.ui

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fullpagedeveloper.mytablayout.R
import com.fullpagedeveloper.mytablayout.ui.home.HomeFragment
import com.fullpagedeveloper.mytablayout.ui.profile.ProfileFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_txt_1, R.string.tab_txt_2, R.string.tab_txt_3)

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        /*var fragment: Fragment? = null
        when(position){
            0 -> fragment = HomeFragment()
            1 -> fragment = ProfileFragment()
        }
        return fragment as Fragment*/

        val fragment = HomeFragment.newInstance(position + 1)
        return fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}