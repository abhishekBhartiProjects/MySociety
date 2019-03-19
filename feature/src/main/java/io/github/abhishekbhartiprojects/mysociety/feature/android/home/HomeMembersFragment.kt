package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.Common
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import kotlinx.android.synthetic.main.home_members_fragment.*
import kotlinx.android.synthetic.main.home_members_fragment.view.*

class HomeMembersFragment: Fragment() {

    lateinit var allMembersInfo: SheetDataMemberInfo

    companion object {
        val ALL_MEMBERS_DATA = "all_members_data"

        fun newInstance(bundle: Bundle) = HomeMembersFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.home_members_fragment, container, false)

        setupUI(view)

        return view
    }

    private fun setupUI(view: View){
        arguments.let {
            if(it != null && it.containsKey(ALL_MEMBERS_DATA)){
                allMembersInfo = it.getParcelable(ALL_MEMBERS_DATA)
                if(allMembersInfo != null && allMembersInfo.rows != null && allMembersInfo.rows.size > 0){
                    setupAdapter(view, allMembersInfo.rows)
                } else {
                    showEmptyScreen()
                }
            } else {
                showEmptyScreen()
            }
        }
    }

    private fun setupAdapter(view: View, membersList: List<MemberInfoRow>){
        var adapter = HomeMembersAdapter(membersList)
        view.rv_members.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.rv_members.adapter = adapter
    }

    private fun showEmptyScreen(){
        if(context != null){
            Common.showToastShort(context!!, "No Member Found")
        }
    }
}