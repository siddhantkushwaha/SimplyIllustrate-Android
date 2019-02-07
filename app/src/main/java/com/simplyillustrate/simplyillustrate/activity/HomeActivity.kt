package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.pojo.User

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.i(HomeActivity::class.java.toString(), FirebaseAuth.getInstance().uid)

        val user = User()
        user.userId = FirebaseAuth.getInstance().uid
        user.email = FirebaseAuth.getInstance().currentUser!!.email
        RestAPI.requestWriteUser(user)
    }

//    TODO - don't delete because this piece of code is often required
//    object : Callback<JsonObject> {
//
//        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//
//        }
//
//        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//
//        }
//    }
}
