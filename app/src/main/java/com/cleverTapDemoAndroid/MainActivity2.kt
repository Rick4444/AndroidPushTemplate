package com.cleverTapDemoAndroid

import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.PushPermissionResponseListener
import java.util.*
import kotlin.collections.HashMap
import com.clevertap.android.sdk.inapp.CTLocalInApp
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener

class MainActivity2 : AppCompatActivity(), CTPushNotificationListener,
    PushPermissionResponseListener {

    var cleverTapDefaultInstance: CleverTapAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
        cleverTapDefaultInstance!!.enableDeviceNetworkInfoReporting(true)
        CleverTapAPI.setDebugLevel(3)
        val btn = findViewById<Button>(R.id.push)
        btn.setOnClickListener {
            if (!cleverTapDefaultInstance?.isPushPermissionGranted!!){
                askForNotificationPermission()
                Toast.makeText(applicationContext, "Enable Notification", Toast.LENGTH_SHORT).show()
            }
        }
        cleverTapDefaultInstance?.ctPushNotificationListener = this;
        cleverTapDefaultInstance?.registerPushPermissionNotificationResponseListener(this)
        onUserLoginMethod()

    }

    override fun onPushPermissionResponse(accepted: Boolean) {
        Log.d("Clevertap", "onPushPermissionResponse :  InApp---> response() called accepted=$accepted")
        if (accepted) {
            Log.d("TAG", "push permission allowed: ")
            CleverTapAPI.createNotificationChannel(getApplicationContext(), "xiaominew", "CT-PushAndroidNew",
                "Test-NotificationsAndroid", NotificationManager.IMPORTANCE_HIGH, true);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cleverTapDefaultInstance?.unregisterPushPermissionNotificationResponseListener(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            CleverTapAPI.getDefaultInstance(applicationContext)?.pushNotificationClickedEvent(intent.extras)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {

        Toast.makeText(applicationContext, "Enable Notification now", Toast.LENGTH_SHORT).show()

        val jsonObject = CTLocalInApp.builder()
            .setInAppType(CTLocalInApp.InAppType.HALF_INTERSTITIAL)
            .setTitleText("Get Notified \uD83D\uDD14")
            .setMessageText("Please enable notifications on your device to use Push Notifications.")
            .followDeviceOrientation(true)
            .setPositiveBtnText("Allow")
            .setNegativeBtnText("Cancel")
            .build()
        cleverTapDefaultInstance?.promptPushPrimer(jsonObject)

    }



    private fun onUserLoginMethod() {
        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Rameshwar Gupta"
        profileUpdate["Identity"] = 612339822032
        profileUpdate["Email"] = "rameshwargct@gmail.com"
        profileUpdate["Phone"] = "+919990999999"
        profileUpdate["Gender"] = "M"
        profileUpdate["DOB"] = Date()
        cleverTapDefaultInstance?.pushProfile(profileUpdate)
    }


    fun sendEventsOnProfile() {

        val prodViewedAction = mapOf(
            "Product Name" to "Casio Chronograph Watch",
            "Category" to "Mens Accessories",
            "Price" to 59.99,
            "Date" to Date())
        cleverTapDefaultInstance?.pushEvent("Product viewed", prodViewedAction)
    }

    override fun onNotificationClickedPayloadReceived(payload: java.util.HashMap<String, Any>?) {
        TODO("Not yet implemented")
    }
}

