package io.github.abhishekbhartiprojects.mysociety.feature.android.service

import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SheetService {
    @GET("api")
    fun getMemberInfoSheetData(@Query("id") id: String,
                             @Query("sheet") sheet_no: String): Single<SheetDataMemberInfo>
}