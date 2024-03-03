package com.example.viewpagerpagesindication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MyPagesAdapter(val fm: FragmentManager) :
    androidx.fragment.app.FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var titles = arrayOf(" p1 ", " p2 ", "p3", "p4")

    private val mPagerFragments: MutableList<Fragment> = ArrayList()

    fun getDataFromAdapter(): List<Fragment> {
        return mPagerFragments
    }

    override fun getItem(position: Int): Fragment {
//        val bundle = Bundle()
//        bundle.putInt(LOCATION, position)
//        bundle.putInt(OBJ_ID, objId)
//        val amonSubPageFrag = AmonaweriSubPageFrag.newInstance()
//        amonSubPageFrag.arguments = bundle
        return FirstFragment()
    }

    override fun getCount(): Int {
        return 4
    }

    fun setTitles(titles: Array<String>) {
        this.titles = titles
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}