package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.MySharedPreference
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo

class HomeActivity : AppCompatActivity() {
    private var loggedInMemberData: MemberInfoRow? = null
    private var allMembersData: SheetDataMemberInfo? = null
    private var memberId: String = ""

    companion object {
        val MEMBER_ID = "member_id"
        val ALL_MEMBER_DATA = "all_member_data"

        fun start(context: Context, loggedInMemberId: String, allMembersData: SheetDataMemberInfo){
            var intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(MEMBER_ID, loggedInMemberId)
            intent.putExtra(ALL_MEMBER_DATA, allMembersData)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(MySharedPreference.getBooleanValue(MySharedPreference.KEY_IS_LOGGED_IN, false)){
            getIntentData()
        }
    }

    private fun getIntentData(){
        if(intent != null){
            allMembersData = intent.getParcelableExtra(ALL_MEMBER_DATA)
            memberId = intent.getStringExtra(MEMBER_ID)
        }
    }
}
