package com.sibmentor.appointment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var Loginbtn: Button
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()

        loginbtn.setOnClickListener {
            //region LoginButtonFunctionality
            try {

                val email = username.text.toString().trim()
                val passwords = password.text.toString().trim()

                if (email.isEmpty()) {
                    username.error = "Email Required"
                    username.requestFocus()
                    return@setOnClickListener
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    username.error = "Valid Email Required"
                    username.requestFocus()
                    return@setOnClickListener
                }

                if (passwords.isEmpty() || passwords.length < 6) {
                    password.error = "6 char password required"
                    password.requestFocus()
                    return@setOnClickListener
                }
                /*username.isEnabled=false
                password.isEnabled=false*/
                loginUser(email, passwords)

            } catch (e: Exception) {
                loginbtn.error = e.message
            }
//endregion

        }
        register.setOnClickListener(
            View.OnClickListener {
                var Intent = Intent(this, UserSignup::class.java)
                startActivity(Intent)
            }
        )
        mentorregister.setOnClickListener(
            View.OnClickListener {
                var Intent = Intent(this, MentorRegistration::class.java)
                startActivity(Intent)
            }
        )
        forget_link.setOnClickListener(View.OnClickListener {
            var Intent = Intent(this, passwordReset::class.java)
            startActivity(Intent)
        })
    }


    private fun loginUser(email: String, password: String) {

        loading.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {

                    task ->
                loading.visibility = View.GONE
                if (task.isSuccessful) {

                    login()

                } else if (task.isCanceled) {
                    task.exception?.message?.let {
                        toast(it)
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    task.exception?.message?.let {
                        toast(it)
                        //Toast.makeText( this, "Login Failed", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()

        }
    }
}
