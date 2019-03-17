package io.github.abhishekbhartiprojects.mysociety.feature.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.abhishekbhartiprojects.mysociety.feature.android.repository.SheetRepository
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SheetViewModel(var sheetRepository: SheetRepository): ViewModel() {
    var memberInfo: MutableLiveData<SheetDataMemberInfo> = MutableLiveData()

    fun getMemberInfo(){
        sheetRepository.getSocietyMemberInfo()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({onMemberInfoResponse(it)},{onMemberInfoError(it)})
    }

    private fun onMemberInfoResponse(sheetDataMemberInfo: SheetDataMemberInfo){
        memberInfo.value = sheetDataMemberInfo
    }

    private fun onMemberInfoError(throwable: Throwable){
        Log.e("SheetViewModel", "onMemberInfoError got error")
    }
}