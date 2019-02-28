package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.fragments.LearningFragment
import com.simplyillustrate.simplyillustrate.fragments.PostsFragment
import com.simplyillustrate.simplyillustrate.fragments.QuestionsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_navigation_drawer.*

class HomeActivity : AppCompatActivity() {

    var isAtHome: Boolean = false

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
        displayFragment(R.id.questions_id)
        drawer_layout.addDrawerListener(toggle)
        navigation_view.setNavigationItemSelectedListener {

            displayFragment(it.itemId)
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun displayFragment(id: Int) {
        val fragment = when (id) {
            R.id.questions_id -> {
                Toast.makeText(this, "Question id clicked", Toast.LENGTH_SHORT).show();
                isAtHome = true
                QuestionsFragment.getNewInstance()
            }
            R.id.posts_id -> {
                Toast.makeText(this, "Posts id clicked", Toast.LENGTH_SHORT).show();
                isAtHome = false
                PostsFragment.getNewInstance()
            }
            R.id.learning_id -> {
                Toast.makeText(this, "Learning id clicked", Toast.LENGTH_SHORT).show();
                isAtHome = false
                LearningFragment()
            }
            else -> {
                QuestionsFragment.getNewInstance()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.commit()

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
        if (!isAtHome) {
            displayFragment(R.id.questions_id)
        } else {
            super.onBackPressed();
        }
    }
}
