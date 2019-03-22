package io.github.abhishekbhartiprojects.mysociety.feature.android.editMember

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo

class EditMemberActivity : AppCompatActivity() {

    var loggedInMemberData: MemberInfoRow? = null
    var allMembersData: SheetDataMemberInfo? = null
    var isAdmin: Boolean = false
    var requestCode: Int = -1

    companion object {
        val IS_ADMIN = "is_admin"
        val REQUEST_CODE = "request_code"
        val LOGGEDIN_MEMBER_DATA = "loggedin_member_data"
        val ALL_MEMBER_DATA = "all_member_data"

        val REQUEST_CODE_ADD_MEMBER = 100
        val REQUEST_CODE_EDIT_DELETE_MEMBER = 101
        val REQUEST_CODE_EDIT_SELF = 102
        val REQUEST_CODE_EDIT_FAMILY = 103

        fun start(context: Context, isAdmin: Boolean, requestCode: Int,
                  loggedInMemberData: MemberInfoRow?, allMembersData: SheetDataMemberInfo?){
            var intent = Intent(context, EditMemberActivity::class.java)
            intent.putExtra(IS_ADMIN, isAdmin)
            intent.putExtra(REQUEST_CODE, requestCode)
            intent.putExtra(LOGGEDIN_MEMBER_DATA, loggedInMemberData)
            intent.putExtra(ALL_MEMBER_DATA, allMembersData)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_member_activity)

        getIntentData()
        initFragment()
    }

    private fun getIntentData(){
        if(intent != null){
            isAdmin = intent.getBooleanExtra(IS_ADMIN, false)
            requestCode = intent.getIntExtra(REQUEST_CODE, -1)
            loggedInMemberData = intent.getParcelableExtra(LOGGEDIN_MEMBER_DATA)
            allMembersData = intent.getParcelableExtra(ALL_MEMBER_DATA)
        }
    }

    private fun initFragment(){
        when(requestCode){
            REQUEST_CODE_ADD_MEMBER -> {
                var bundle = Bundle()
                bundle.putParcelable(AddMemberFragment.LOGGEDIN_MEMBER_DATA, loggedInMemberData)
                bundle.putParcelable(AddMemberFragment.ALL_MEMBER_DATA, allMembersData)
                supportFragmentManager.beginTransaction().add(R.id.fl_edit_member_container, AddMemberFragment.newInstance(bundle), "AddMemberFragment").commit()
            }
            REQUEST_CODE_EDIT_DELETE_MEMBER -> {}
            REQUEST_CODE_EDIT_SELF -> {}
            REQUEST_CODE_EDIT_FAMILY -> {}
        }
    }
}
