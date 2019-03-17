package io.github.abhishekbhartiprojects.mysociety.feature.android.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.github.abhishekbhartiprojects.mysociety.feature.android.Constants
import io.github.abhishekbhartiprojects.mysociety.feature.android.service.SheetService
import io.github.abhishekbhartiprojects.mysociety.feature.model.FirebaseRemoteConfigHelper
import io.github.abhishekbhartiprojects.mysociety.feature.model.SheetDataMemberInfo
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SheetRepository(context: Context) {
    private var service:SheetService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(FirebaseRemoteConfigHelper.getInstance().sheetEndpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()


        service = retrofit.create(SheetService::class.java)
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true

        return isConnected
    }

    fun getSocietyMemberInfo(): Single<SheetDataMemberInfo> {
        var sheet_id = getSocitySheetId()
        var sheet_no = FirebaseRemoteConfigHelper.getInstance().memberInfoPage

        return service.getMemberInfoSheetData(sheet_id, sheet_no.toString())
    }

    private fun getSocitySheetId():String{
        if(Constants.SOCITY.equals(Constants.HYDE_PARK)){
            return FirebaseRemoteConfigHelper.getInstance().hydeParkSheetId
        }

        return ""
    }
}