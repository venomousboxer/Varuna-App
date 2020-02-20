package com.example.venomousboxer.varunaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var etEmail : TextInputEditText
    lateinit var etPassword : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.inputEditTextEmailLogin)
        etPassword = findViewById(R.id.inputEditTextPasswordLogin)
    }

    fun signUp(view: View) {
        //Goto SignUp Activity
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun signIn(view: View) {
        //Login

        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    val intent = Intent(this,FormActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Please enter valid Email Id and Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
