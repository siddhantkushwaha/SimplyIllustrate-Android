package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.simplyillustrate.simplyillustrate.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_navigation_drawer.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_navigation_drawer)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this@HomeActivity,
            drawer_layout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.syncState()
        drawer_layout.addDrawerListener(toggle)
        navigation_view.setNavigationItemSelectedListener {

            when (it.itemId) {

            }

            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
