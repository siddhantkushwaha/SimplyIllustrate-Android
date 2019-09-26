package com.simplyillustrate.simplyillustrate.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonObject
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.entity.User
import com.simplyillustrate.simplyillustrate.fragments.LearningFragment
import com.simplyillustrate.simplyillustrate.fragments.PostsFragment
import com.simplyillustrate.simplyillustrate.fragments.QuestionsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_navigation_drawer.*
import kotlinx.android.synthetic.main.layout_navigation_header.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    var isAtHome: Boolean = false

    // stores oid via making a call to the server
    companion object {
        var userId: String? = null
    }

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

            when (it.itemId) {

                R.id.logout_id -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    finish()
                }

                R.id.profile_id -> {
                    startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                }

                else -> {
                    displayFragment(it.itemId)
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        val user = User()
        user.firebaseId = FirebaseAuth.getInstance().uid
        user.email = FirebaseAuth.getInstance().currentUser!!.email
        RestAPI.requestWriteUser(user, object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                userId = response.body()?.get("_id")?.asString
            }
        })

        navigation_view.getHeaderView(0).username.text =
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) ?: "None"
        navigation_view.getHeaderView(0).email.text =
            FirebaseAuth.getInstance().currentUser?.email ?: "None"
    }

    private fun displayFragment(id: Int) {
        val fragment = when (id) {
            R.id.questions_id -> {
                Toast.makeText(this, "Question id clicked", Toast.LENGTH_SHORT).show()
                isAtHome = true
                QuestionsFragment.getNewInstance()
            }
            R.id.posts_id -> {
                Toast.makeText(this, "Posts id clicked", Toast.LENGTH_SHORT).show()
                isAtHome = false
                PostsFragment.getNewInstance()
            }
            R.id.learning_id -> {
                Toast.makeText(this, "Learning id clicked", Toast.LENGTH_SHORT).show()
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
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        if (!isAtHome) {
            displayFragment(R.id.questions_id)
        } else {
            super.onBackPressed()
        }
    }
}
