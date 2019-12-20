package com.dicoding.event.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
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

        setSupportActionBar(activityHomeToolbar)

        val drawerToggleButton = ActionBarDrawerToggle(this, activityHomeDrawerLayout, null, R.string.open, R.string.close)

        activityHomeDrawerLayout.addDrawerListener(drawerToggleButton)
        drawerToggleButton.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_home)
        supportActionBar?.title = ""
        supportActionBar?.elevation = 0f

    }
}
