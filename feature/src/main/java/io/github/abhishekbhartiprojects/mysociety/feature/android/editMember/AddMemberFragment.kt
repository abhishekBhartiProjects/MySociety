package io.github.abhishekbhartiprojects.mysociety.feature.android.editMember

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo

class AddMemberFragment: Fragment() {

    var loggedInMemberData: MemberInfoRow? = null
    var allMembersData: SheetDataMemberInfo? = null

    companion object {
        fun newInstance(bundle: Bundle) = AddMemberFragment().apply { arguments = bundle }

        val LOGGEDIN_MEMBER_DATA = "loggedin_member_data"
        val ALL_MEMBER_DATA = "all_member_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.add_member_fragment, container, false)

        getDataFromBundle()
        setupUI()

        return view
    }

    private fun getDataFromBundle(){
        arguments.let {
            if(it != null && it.containsKey(LOGGEDIN_MEMBER_DATA) && it.containsKey(ALL_MEMBER_DATA)){
                loggedInMemberData = it.getParcelable(LOGGEDIN_MEMBER_DATA)
                allMembersData = it.getParcelable(ALL_MEMBER_DATA)
            }
        }
    }

    private fun setupUI(){

    }
}