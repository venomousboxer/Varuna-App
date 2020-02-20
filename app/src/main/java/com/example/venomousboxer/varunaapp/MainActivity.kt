package com.example.venomousboxer.varunaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var etEmail : TextInputEditText
    lateinit var etPassword : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.inputEditTextEmail)
        etPassword = findViewById(R.id.inputEditTextPassword)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if(currentUser!=null){
            val intent = Intent(this,FormActivity::class.java)
            startActivity(intent)
        }
    }

    fun signUp(view: View) {
        //SignUp

        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MainActivity", "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("MainActivity", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signIn(view: View) {
        //Goto Login Activity
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
