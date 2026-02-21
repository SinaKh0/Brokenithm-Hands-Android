package com.github.brokenithm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class BrokenithmApplication : Application() {

    abstract class BasePreference<T>(context: Context, fileName: String) {
        protected val config: SharedPreferences = context.getSharedPreferences(fileName, MODE_PRIVATE)
        abstract fun value(): T
        abstract fun update(value: T)
    }

    abstract class Settings<T>(context: Context) : BasePreference<T>(context, settings_preference)

    open class StringPreference(
        context: Context,
        private val key: String,
        private val defValue: String
    ) : Settings<String>(context) {
        override fun value() = config.getString(key, defValue) ?: defValue
        override fun update(value: String) = config.edit().putString(key, value).apply()
    }

    open class BooleanPreference(
        context: Context,
        private val key: String,
        private val defValue: Boolean
    ) : Settings<Boolean>(context) {
        override fun value() = config.getBoolean(key, defValue)
        override fun update(value: Boolean) = config.edit().putBoolean(key, value).apply()
    }

    open class IntegerPreference(
        context: Context,
        private val key: String,
        private val defValue: Int
    ) : Settings<Int>(context) {
        override fun value() = config.getInt(key, defValue)
        override fun update(value: Int) = config.edit().putInt(key, value).apply()
    }

    open class FloatPreference(
        context: Context,
        private val key: String,
        private val defValue: Float
    ) : Settings<Float>(context) {
        override fun value() = config.getString(key, defValue.toString())?.toFloat() ?: defValue
        override fun update(value: Float) = config.edit().putString(key, value.toString()).apply()
    }

    lateinit var lastServer: StringPreference
    lateinit var enableAir: BooleanPreference
    lateinit var showDelay: BooleanPreference
    lateinit var tcpMode: BooleanPreference
    lateinit var enableNFC: BooleanPreference
    lateinit var useFrontCamera: BooleanPreference
    lateinit var cameraAirSensitivity: FloatPreference
    lateinit var roiCenterX: IntegerPreference
    lateinit var roiCenterY: IntegerPreference
    lateinit var roiWidth: IntegerPreference
    lateinit var roiSpacing: IntegerPreference

    override fun onCreate() {
        super.onCreate()
        lastServer = StringPreference(this, "server", "")
        enableAir = BooleanPreference(this, "enable_air", true)
        showDelay = BooleanPreference(this, "show_delay", false)
        tcpMode = BooleanPreference(this, "tcp_mode", false)
        enableNFC = BooleanPreference(this, "enable_nfc", true)
        useFrontCamera = BooleanPreference(this, "use_front_camera", true)
        cameraAirSensitivity = FloatPreference(this, "camera_air_sensitivity", 0.15f)
        roiCenterX = IntegerPreference(this, "roi_center_x", 50)
        roiCenterY = IntegerPreference(this, "roi_center_y", 50)
        roiWidth = IntegerPreference(this, "roi_width", 30)
        roiSpacing = IntegerPreference(this, "roi_spacing", 2)
    }

    companion object {
        private const val settings_preference = "settings"
    }
}