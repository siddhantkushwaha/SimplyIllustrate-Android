package com.simplyillustrate.simplyillustrate.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplyillustrate.simplyillustrate.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn_save.setOnClickListener {
            finish()
        }
    }
}
