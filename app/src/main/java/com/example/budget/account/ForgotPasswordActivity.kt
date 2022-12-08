package com.example.budget.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.budget.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var verifyEmail :EditText
    lateinit var submit :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password2)
        verifyEmail = findViewById(R.id.reset_email)
        submit = findViewById(R.id.submit)
        submit.setOnClickListener{
            var email : String = verifyEmail.text.toString().trim{it <= ' '}
            if (email.isEmpty()){
                Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{
                        task ->if (task.isSuccessful){
                            Toast.makeText(this,"Email sent!",Toast.LENGTH_LONG).show()
                        finish()
                        }else{
                            Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                        finish()
                        }
                    }
            }
        }
    }
}