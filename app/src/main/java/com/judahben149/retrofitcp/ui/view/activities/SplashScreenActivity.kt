package com.judahben149.retrofitcp.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.judahben149.retrofitcp.R
import com.judahben149.retrofitcp.databinding.SplashScreenBinding

class SplashScreenActivity: AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_image_animation)

        binding.splashScreenImage.startAnimation(imageAnimation)

        val splashScreenTimeout = 1000
        val mainActivityIntent = Intent(this, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(mainActivityIntent)
            finish()
        }, splashScreenTimeout.toLong())

    }
}