package com.cleverTapDemoAndroid

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.cleverTapDemoAndroid.databinding.ActivityMainBinding
import com.clevertap.android.sdk.CleverTapAPI
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var cleverTapDefaultInstance: CleverTapAPI? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // each of the below mentioned fields are optional
//        val profileUpdate = HashMap<String, Any>()
//        profileUpdate["Name"] = "Vedhika Android"
//        profileUpdate["Identity"] = 29374889989889
//        profileUpdate["Email"] = "vedhi@clevertapnjnmnm.com"
//        profileUpdate["Phone"] = "+919540639999"
//        profileUpdate["Gender"] = "F"
//        profileUpdate["DOB"] = Date()
//        profileUpdate["MSG-email"] = false
//        profileUpdate["MSG-push"] = true
//        profileUpdate["MSG-sms"] = false
//        profileUpdate["MSG-whatsapp"] = true
//        CleverTapAPI.getDefaultInstance(applicationContext)?.onUserLogin(profileUpdate)




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}