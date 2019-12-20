package com.dicoding.event.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.dicoding.event.R
import com.dicoding.event.utils.UserPreference
import com.dicoding.event.utils.getUserPreference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userPreference = getUserPreference()

        Toast.makeText(this, userPreference.toString(), Toast.LENGTH_LONG).show()

        activityHomeImageButtonDrawer.setOnClickListener {
            with(activityHomeDrawerLayout) {
                if(!isDrawerOpen(GravityCompat.START)) {
                    openDrawer(GravityCompat.START)
                    return@setOnClickListener
                }
                closeDrawer(GravityCompat.START)
            }
        }
    }
}
