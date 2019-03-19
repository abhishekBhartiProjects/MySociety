package io.github.abhishekbhartiprojects.mysociety.feature.android.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import io.github.abhishekbhartiprojects.mysociety.feature.R
import io.github.abhishekbhartiprojects.mysociety.feature.android.login.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity() {

    lateinit var aniSlide: Animation
    lateinit var aniFadeIn: Animation

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, OnboardingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_black)
        setContentView(R.layout.activity_onboarding)
        aniSlide = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        aniFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

        hideViews()
        setClickListeners()
    }

    private fun hideViews(){
        tv_society_name.visibility = View.GONE
        tv_society_description.visibility = View.GONE
        tv_login.visibility = View.GONE
        tv_not_member.visibility = View.GONE
        tv_get_invitation.visibility = View.GONE
        tv_or.visibility = View.GONE
        iv_proceed_as_guest.visibility = View.GONE
        tv_proceed_as_guest.visibility = View.GONE
        rl_proceed.visibility = View.GONE
    }

    private fun setClickListeners(){
        tv_login.setOnClickListener {
            startActivity(Intent(it.context, LoginActivity::class.java))
        }
        tv_get_invitation.setOnClickListener {  }
        iv_proceed_as_guest.setOnClickListener {  }
    }

    override fun onResume() {
        super.onResume()

        animateViews()
    }

    private fun animateViews(){
        var handler = Handler()
        handler.postDelayed({
            tv_society_name.visibility = View.VISIBLE
            tv_society_description.visibility = View.VISIBLE
            tv_login.visibility = View.VISIBLE

            tv_society_name.startAnimation(aniSlide)
            tv_society_description.startAnimation(aniSlide)
            tv_login.startAnimation(aniSlide)}, 500)


        handler.postDelayed({
            tv_not_member.visibility = View.VISIBLE
            tv_get_invitation.visibility = View.VISIBLE
            tv_or.visibility = View.VISIBLE
            iv_proceed_as_guest.visibility = View.VISIBLE
            tv_proceed_as_guest.visibility = View.VISIBLE
            rl_proceed.visibility = View.VISIBLE

            tv_not_member.startAnimation(aniFadeIn)
            tv_get_invitation.startAnimation(aniFadeIn)
            tv_or.startAnimation(aniFadeIn)
            iv_proceed_as_guest.startAnimation(aniFadeIn)
            tv_proceed_as_guest.startAnimation(aniFadeIn)
            rl_proceed.startAnimation(aniFadeIn)

        }, 1500)



    }
}
