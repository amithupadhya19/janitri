package com.example.janitri_innovations_amith_upadhya

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        //go to signup page
        val mGoToSignupTV: TextView =findViewById(R.id.goToSignupPage)
        mGoToSignupTV.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }
        //initialise login activity fields
        val mEmailField:EditText=findViewById(R.id.loginEmailField)


        val mPasswordField:EditText=findViewById(R.id.loginPasswordField)

        //login button click listener
        val mLoginButton:Button=findViewById(R.id.loginButton)
        mLoginButton.setOnClickListener {
            val email:String=mEmailField.text.toString()
            val password:String=mPasswordField.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
    //to check if a user is already logged in?
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    fun updateUI(currentuser : FirebaseUser?){
            if (currentuser!=null){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
    }
}