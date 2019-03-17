package io.github.abhishekbhartiprojects.mysociety.feature.android

import android.content.Context
import android.widget.Toast

class Common {
    companion object {
        fun showToastShort(context: Context, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun showToastLong(context: Context, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }
}