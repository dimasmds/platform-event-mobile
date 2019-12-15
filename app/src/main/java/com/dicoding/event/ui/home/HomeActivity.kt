package com.dicoding.event.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.event.R
import com.dicoding.event.utils.UserPreference
import com.dicoding.event.utils.getUserPreference

class HomeActivity : AppCompatActivity() {

    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userPreference = getUserPreference()

        Toast.makeText(this, userPreference.toString(), Toast.LENGTH_LONG).show()
    }
}
