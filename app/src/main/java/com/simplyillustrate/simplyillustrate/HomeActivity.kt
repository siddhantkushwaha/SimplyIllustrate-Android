package com.simplyillustrate.simplyillustrate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonObject
import com.simplyillustrate.simplyillustrate.pojo.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.i(HomeActivity::class.java.toString(), FirebaseAuth.getInstance().uid)

        val user = User()
        user.userId = FirebaseAuth.getInstance().uid
        user.email = FirebaseAuth.getInstance().currentUser!!.email
        RestAPI.requestWriteUser(user, object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        user.name = "Siddhant Kushwaha"
        user.phoneNumber = "+917351651000"
        RestAPI.requestWriteUser(user, object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
