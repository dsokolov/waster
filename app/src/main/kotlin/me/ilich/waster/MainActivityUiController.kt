package me.ilich.waster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find

class MainActivityUiController(
        val activity: AppCompatActivity
) {

    fun onCreate(savedInstanceState: Bundle?) {
        activity.setContentView(R.layout.activity_main)
        val toolbar: Toolbar = activity.find(R.id.toolbar)
        activity.setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            activity.supportFragmentManager.beginTransaction().
                    replace(R.id.container_content, PurchaseFragment.create()).
                    commit()
        }
    }

}