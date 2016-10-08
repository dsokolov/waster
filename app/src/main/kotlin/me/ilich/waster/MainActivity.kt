package me.ilich.waster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val uiController = MainActivityUiController(this)
    var billingController = BillingController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        billingController.onCreate()
        uiController.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        billingController.onDestroy()
    }

}
