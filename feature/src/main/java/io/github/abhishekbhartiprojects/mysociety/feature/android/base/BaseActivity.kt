package io.github.abhishekbhartiprojects.mysociety.feature.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.github.abhishekbhartiprojects.mysociety.feature.viewModel.SheetViewModel
import io.github.abhishekbhartiprojects.mysociety.feature.viewModelFactory.SheetViewModelFactory

open class BaseActivity: AppCompatActivity() {

    lateinit var sheetViewModel: SheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSheetViewModel()
        observeSheetViewModel()

    }

    private fun initSheetViewModel(){
        var factory = SheetViewModelFactory(this)
        sheetViewModel = ViewModelProviders.of(this, factory).get(SheetViewModel::class.java)
    }

    private fun observeSheetViewModel(){
        sheetViewModel.memberInfo.observe(this, Observer { onMemberInfoResponse(it) })
    }

    protected fun getMemberInfo(){
        sheetViewModel.getMemberInfo()
    }

    //handle response
    private fun onMemberInfoResponse(sheetDataMemberInfo: SheetDataMemberInfo){
        if(sheetDataMemberInfo != null){

        }
    }
}