package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.simplyillustrate.simplyillustrate.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn_save.setOnClickListener {
            finish()
        }

        email.text = FirebaseAuth.getInstance().currentUser?.email ?: "None"

        username.setText(
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) ?: "None"
        )
    }
}
