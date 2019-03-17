package io.github.abhishekbhartiprojects.mysociety.feature.android

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference {

    constructor(context: Context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    companion object {
        val SHARED_PREFERENCES = "my_preference"
        val KEY_IS_LOGGED_IN = "key_is_logged_in"
        val KEY_MEMBER_ID = "key_member_id"


        lateinit var sharedPreferences: SharedPreferences
//        var mInstance: MySharedPreference? = null

        fun instantiateSharedPreference(context: Context){
//            if(mInstance == null){
//                mInstance = MySharedPreference(context)
//            }
            MySharedPreference(context)
        }

        fun deleteKey(key: String){
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove(key)
            editor.commit()
        }

        fun setStringValue(key: String, value: String){
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.commit()
        }

        fun getStringValue(key: String): String{
            return sharedPreferences.getString(key, "")
        }

        fun setBooleanValue(key: String, value: Boolean){
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.commit()
        }

        fun getBooleanValue(key: String, default: Boolean): Boolean{
            return sharedPreferences.getBoolean(key, default)
        }
    }




}