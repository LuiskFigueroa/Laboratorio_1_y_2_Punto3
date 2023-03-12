package com.lcarlosfb.p3.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lcarlosfb.p3.R
import com.lcarlosfb.p3.presentation.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		val timer = Timer()
		timer.schedule(timerTask {
			val intent = Intent(this@SplashActivity, MainActivity::class.java)
			startActivity(intent)
			finish()
		}, 2000)

	}
}