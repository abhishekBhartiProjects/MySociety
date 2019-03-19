package io.github.abhishekbhartiprojects.mysociety.feature.android.login

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.Common
import io.github.abhishekbhartiprojects.mysociety.feature.android.MySharedPreference
import io.github.abhishekbhartiprojects.mysociety.feature.android.base.BaseActivity
import io.github.abhishekbhartiprojects.mysociety.feature.android.home.HomeActivity
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.github.abhishekbhartiprojects.mysociety.feature.viewModel.SheetViewModel
import io.github.abhishekbhartiprojects.mysociety.feature.viewModelFactory.SheetViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sheetViewModel: SheetViewModel
    private var mSheetDataMemberInfo: SheetDataMemberInfo? = null
    private var memberId: String = ""
    private var pin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initSheetViewModel()
        observeSheetViewModel()
        setClickListeners()
    }

    private fun initSheetViewModel(){
        var factory = SheetViewModelFactory(this)
        sheetViewModel = ViewModelProviders.of(this, factory).get(SheetViewModel::class.java)
    }

    private fun observeSheetViewModel(){
        sheetViewModel.memberInfo.observe(this, Observer { onMemberInfoResponse(it) })
    }

    private fun onMemberInfoResponse(sheetDataMemberInfo: SheetDataMemberInfo){
        verifyLogin(sheetDataMemberInfo)
    }

    private fun setClickListeners(){
        tv_login.setOnClickListener {
            if(et_member_id.text == null || TextUtils.isEmpty(et_member_id.text.toString())){
                Common.showToastShort(this, "Please enter member Id")
            } else if(et_pin.text == null || TextUtils.isEmpty(et_pin.text.toString())){
                Common.showToastShort(this, "Please enter login pin")
            } else {
                Common.showToastLong(this, "Verifying...")
                getDataAndverifyLogin(et_member_id.text.toString(), et_pin.text.toString())

                tv_login.isEnabled = false
                var handler = Handler()
                handler.postDelayed({tv_login.isEnabled = true}, 2000)
            }
        }
    }

    private fun verifyLogin(sheetDataMemberInfo: SheetDataMemberInfo?){
        tv_login.isEnabled = false

        if(sheetDataMemberInfo != null && sheetDataMemberInfo.rows != null && sheetDataMemberInfo.rows.size > 0){
            this.mSheetDataMemberInfo = sheetDataMemberInfo
            var isValidMember = false

            for (memberInfoRow in sheetDataMemberInfo.rows){
                if(memberInfoRow.memberid.equals(memberId)){
                    isValidMember = true

                    if(memberInfoRow.pin.equals(pin)){
                        loginVerified(memberInfoRow, sheetDataMemberInfo)
                    } else {
                        Common.showToastShort(this, "Incorrect Pin")
                    }
                    break
                }
            }

            if(!isValidMember){
                Common.showToastShort(this, "Member not found!")
            }
        }
    }

    private fun getDataAndverifyLogin(memberId: String, pin: String){
        this.memberId = memberId
        this.pin = pin

        if(mSheetDataMemberInfo == null){
            getMemberInfo()
        } else {
            verifyLogin(mSheetDataMemberInfo)
        }
    }

    private fun getMemberInfo(){
        sheetViewModel.getMemberInfo()
    }

    private fun loginVerified(loggedInMemberData: MemberInfoRow, allMembersData: SheetDataMemberInfo){
        Common.showToastShort(this, "You are successfully logged in")
        setMemberInfoInSharedPreference(loggedInMemberData)
        HomeActivity.start(this, loggedInMemberData.memberid, allMembersData)
    }

    private fun setMemberInfoInSharedPreference(loggedInMemberData: MemberInfoRow){
        MySharedPreference.setBooleanValue(MySharedPreference.KEY_IS_LOGGED_IN, true)
        MySharedPreference.setStringValue(MySharedPreference.KEY_MEMBER_ID, loggedInMemberData.memberid)
    }
}
