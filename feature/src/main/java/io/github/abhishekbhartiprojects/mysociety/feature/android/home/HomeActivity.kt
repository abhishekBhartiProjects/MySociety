package io.github.abhishekbhartiprojects.mysociety.feature.android.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.MySharedPreference
import io.github.abhishekbhartiprojects.mysociety.feature.android.editMember.EditMemberActivity
import io.github.abhishekbhartiprojects.mysociety.feature.model.MemberInfoRow
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.github.abhishekbhartiprojects.mysociety.feature.viewModel.SheetViewModel
import io.github.abhishekbhartiprojects.mysociety.feature.viewModelFactory.SheetViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_home_viewpager.*

class HomeActivity : AppCompatActivity() {

    private lateinit var sheetViewModel: SheetViewModel
    private var loggedInMemberData: MemberInfoRow? = null
    private var allMembersData: SheetDataMemberInfo? = null
    private var memberId: String? = ""

    companion object {
        val MEMBER_ID = "member_id"
        val ALL_MEMBER_DATA = "all_member_data"

        fun start(context: Context){
            context.startActivity(Intent(context, HomeActivity::class.java))
        }

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

        initSheetViewModel()
        observeSheetViewModel()
        initViews()

        if(MySharedPreference.getBooleanValue(MySharedPreference.KEY_IS_LOGGED_IN, false)){
            getIntentData()
        }
    }

    private fun initViews(){
        setSupportActionBar(findViewById(R.id.toolbar))
        var actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        setupDrawerContent()
    }

    private fun setupDrawerContent(){
        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener {
            it.setChecked(true)
            drawer_layout.closeDrawers()

            when(it.itemId){
                R.id.nav_home -> { }
                R.id.nav_edit_profile -> {
                    if(loggedInMemberData != null){
                        showEditSelfPage(loggedInMemberData, allMembersData)
                    }
                }
                R.id.nav_edit_family -> {
                    if(loggedInMemberData != null){
                        showEditFamilyPage(loggedInMemberData, allMembersData)
                    }
                }
                R.id.nav_create_event -> { }
                R.id.nav_logout -> { }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    private fun initSheetViewModel(){
        var factory = SheetViewModelFactory(this)
        sheetViewModel = ViewModelProviders.of(this, factory).get(SheetViewModel::class.java)
    }

    private fun observeSheetViewModel(){
        sheetViewModel.memberInfo.observe(this, Observer { onMemberInfoResponse(it) })
    }

    private fun onMemberInfoResponse(sheetDataMemberInfo: SheetDataMemberInfo){
        if(sheetDataMemberInfo != null && sheetDataMemberInfo.rows != null && sheetDataMemberInfo.rows.size > 0){
            allMembersData = sheetDataMemberInfo
            memberId = MySharedPreference.getStringValue(MySharedPreference.KEY_MEMBER_ID)

            for (memberInfoRow in sheetDataMemberInfo.rows){
                if(memberInfoRow.memberid.equals(memberId)){
                    loggedInMemberData = memberInfoRow
                    break
                }
            }

            setupUi(allMembersData, loggedInMemberData)
        }
    }

    private fun getIntentData(){
        if(intent != null){
            allMembersData = intent.getParcelableExtra(ALL_MEMBER_DATA)
            memberId = intent.getStringExtra(MEMBER_ID)

            if(allMembersData == null){ // when directly come on this screen from launcherActivity
                getMemberInfo()
            } else { // when come from login screen after login
                for (memberInfoRow in allMembersData!!.rows){
                    if(memberInfoRow.memberid.equals(memberId)){
                        loggedInMemberData = memberInfoRow

                        setupUi(allMembersData, loggedInMemberData)
                        break
                    }
                }
            }
        }
    }

    private fun getMemberInfo(){
        sheetViewModel.getMemberInfo()
    }

    private fun setupUi(allMembersData: SheetDataMemberInfo?, loggedInMemberData: MemberInfoRow?){
        setupViewPager(allMembersData, loggedInMemberData)
        tabs.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(allMembersData: SheetDataMemberInfo?, loggedInMemberData: MemberInfoRow?){
        var adapter = HomeViewPagerAdapter(supportFragmentManager, allMembersData, loggedInMemberData)
        viewpager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(loggedInMemberData != null){
            menuInflater.inflate(R.menu.home_overflow_menu, menu)
        }

        menuInflater.inflate(R.menu.home_overflow_menu, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item != null){
            when(item!!.itemId){
                android.R.id.home -> {
                    drawer_layout.openDrawer(GravityCompat.START)
                }

                R.id.menu_add_new_member -> {
                    if(loggedInMemberData != null){
                        showAddMemberPage(loggedInMemberData!!.isisadmin(), loggedInMemberData, allMembersData)
                    }
                }

                R.id.menu_edit_delete_member -> {
                    if(loggedInMemberData != null){
                        showEditMemberPage(loggedInMemberData!!.isisadmin(), loggedInMemberData, allMembersData)
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showAddMemberPage(isAdmin: Boolean, loggedInMemberData: MemberInfoRow?, allMembersData: SheetDataMemberInfo?){
        EditMemberActivity.start(this, isAdmin, EditMemberActivity.REQUEST_CODE_ADD_MEMBER, loggedInMemberData, allMembersData)
    }

    private fun showEditMemberPage(isAdmin: Boolean, loggedInMemberData: MemberInfoRow?, allMembersData: SheetDataMemberInfo?){
        EditMemberActivity.start(this, isAdmin, EditMemberActivity.REQUEST_CODE_EDIT_DELETE_MEMBER, loggedInMemberData, allMembersData)
    }

    private fun showEditSelfPage(loggedInMemberData: MemberInfoRow?, allMembersData: SheetDataMemberInfo?){
        EditMemberActivity.start(this, loggedInMemberData!!.isisadmin(), EditMemberActivity.REQUEST_CODE_EDIT_SELF, loggedInMemberData, allMembersData)
    }

    private fun showEditFamilyPage(loggedInMemberData: MemberInfoRow?, allMembersData: SheetDataMemberInfo?){
        EditMemberActivity.start(this, loggedInMemberData!!.isisadmin(), EditMemberActivity.REQUEST_CODE_EDIT_FAMILY, loggedInMemberData, allMembersData)
    }
}
