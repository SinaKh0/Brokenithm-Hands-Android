package com.github.brokenithm.fragment

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.github.brokenithm.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = "settings"
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val setDecimalEdit = { editText: EditText -> editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL }
        val entries = listOf("camera_air_sensitivity")
        for (entry in entries)
            findPreference<EditTextPreference>(entry)?.setOnBindEditTextListener(setDecimalEdit)
    }
}