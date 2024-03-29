package com.simplyillustrate.simplyillustrate.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.simplyillustrate.simplyillustrate.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    var layoutMode = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layoutMode = savedInstanceState?.getBoolean("layoutMode") ?: true
        refreshLayout()

        toggleLayout.setOnClickListener {
            layoutMode = !layoutMode
            refreshLayout()
        }

        submit.setOnClickListener {
            if (layoutMode)
                login(email.text.toString(), password.text.toString())
            else
                signUp(email.text.toString(), password.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean("layoutMode", layoutMode)
    }

    override fun onStart() {
        super.onStart()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }

    private fun refreshLayout() {
        submit.setText(if (layoutMode) R.string.sign_in else R.string.create_account)
        toggleLayout.setText(if (layoutMode) R.string.ask_sign_in else R.string.ask_create_account)
    }

    private fun signUp(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {
                done()
            } else {

            }

            it.exception?.printStackTrace()
        }
    }

    private fun login(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {
                done()
            } else {

            }

            it.exception?.printStackTrace()
        }
    }

    private fun done() {

        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        if (mAuth.currentUser?.isEmailVerified == false)
            mAuth.currentUser?.sendEmailVerification()
        finish()
    }
}
