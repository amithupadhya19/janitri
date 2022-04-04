package com.example.janitri_innovations_amith_upadhya

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        //initializing signup activity fields
        val mEmailField:EditText=findViewById(R.id.signupEmailField)
        val mPasswordField:EditText=findViewById(R.id.signupPasswordField)
        //signup button on click listener
        val mSignUpButton:Button=findViewById(R.id.signupButton)
        mSignUpButton.setOnClickListener {
            if(!Patterns.EMAIL_ADDRESS.matcher(mEmailField.text.toString()).matches()){
                mEmailField.requestFocus()
                mEmailField.setError("enter valid address")
                return@setOnClickListener
            }
            if (mPasswordField.text.toString().length>=16||mPasswordField.text.toString().length<=7){
                mPasswordField.requestFocus()
                mPasswordField.setError("password should be between 8 to 15 characters")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(mEmailField.text.toString(), mPasswordField.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"added user",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }

    }

}