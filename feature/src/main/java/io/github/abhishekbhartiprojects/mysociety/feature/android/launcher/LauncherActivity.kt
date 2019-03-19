package io.github.abhishekbhartiprojects.mysociety.feature.android.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.MySharedPreference
import io.github.abhishekbhartiprojects.mysociety.feature.android.home.HomeActivity
import io.github.abhishekbhartiprojects.mysociety.feature.android.onboarding.OnboardingActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(MySharedPreference.getBooleanValue(MySharedPreference.KEY_IS_LOGGED_IN, false)){
            HomeActivity.start(this)
        } else {
            OnboardingActivity.start(this)
        }
    }
}
