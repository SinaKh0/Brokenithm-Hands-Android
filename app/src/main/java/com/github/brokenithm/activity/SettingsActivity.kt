package com.github.brokenithm.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.brokenithm.R
import com.github.brokenithm.fragment.SettingsFragment

class SettingsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction().replace(R.id.settings_container, SettingsFragment()).commit()
        val entries = listOf("camera_air_sensitivity")
    }
}