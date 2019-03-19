package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo

class HomeViewPagerAdapter(fm: FragmentManager,
                           allMembersData: SheetDataMemberInfo?,
                           loggedInMemberData: MemberInfoRow?) : FragmentPagerAdapter(fm) {

    private var mFragments: MutableList<Fragment> = arrayListOf()
    private var mFragmentTitles: MutableList<String> = arrayListOf()

    init {
        addFragment(HomeEventFragment.newInstance(null), "EVENTS")
        addFragment(HomeEventFragment.newInstance(null), "MEMBERS")
        addFragment(HomeEventFragment.newInstance(null), "ABOUT SOCIETY")
        addFragment(HomeEventFragment.newInstance(null), "FOR RENT")
    }

    private fun addFragment(fragment: Fragment, title: String){
        mFragments.add(fragment)
        mFragmentTitles.add(title)
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitles.get(position)
    }
}