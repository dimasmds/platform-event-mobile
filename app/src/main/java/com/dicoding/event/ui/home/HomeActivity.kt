package com.dicoding.event.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.dicoding.event.R
import com.dicoding.event.ui.latest.LatestFragment
import com.dicoding.event.ui.myevent.MyEventFragment
import com.dicoding.event.ui.upcoming.UpcomingFragment
import com.dicoding.event.utils.UserPreference
import com.dicoding.event.utils.getUserPreference
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userPreference = getUserPreference()

        activityHomeImageButtonDrawer.setOnClickListener {
            with(activityHomeDrawerLayout) {
                if(!isDrawerOpen(GravityCompat.START)) {
                    openDrawer(GravityCompat.START)
                    return@setOnClickListener
                }
                closeDrawer(GravityCompat.START)
            }
        }

        activityHomeNavigationView.setNavigationItemSelectedListener(this)
        fragmentTransaction(UpcomingFragment.newInstance())
        changeToolbarTitle(getString(R.string.navigation_title_upcoming))
    }


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when(menuItem.itemId) {
            R.id.navigationMenuUpcomingEvent -> {
                fragmentTransaction(UpcomingFragment.newInstance())
                changeToolbarTitle(getString(R.string.navigation_title_upcoming))

            }

            R.id.navigationMenuLastEvent -> {
                fragmentTransaction(LatestFragment.newInstance())
                changeToolbarTitle(getString(R.string.navigation_title_latest))

            }

            R.id.navigationMenuYourEvent -> {
                fragmentTransaction(MyEventFragment.newInstance())
                changeToolbarTitle(getString(R.string.navigation_title_your_event))
            }

            R.id.navigationMenuYourBookmark -> {

            }
        }

        activityHomeDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.activityHomeFrameLayout, fragment).commit()
    }

    private fun changeToolbarTitle(title: String) {
        activityHomeTitleNavigation.text = title
    }

}
