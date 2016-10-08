package me.ilich.waster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {

    var billingController = BillingController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        billingController.onCreate()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.container_content, PurchaseFragment.create()).commit()

    }

    override fun onDestroy() {
        super.onDestroy()
        billingController.onDestroy()
    }

}
