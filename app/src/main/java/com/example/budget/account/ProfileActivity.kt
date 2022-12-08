package com.example.budget.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.budget.ListActivity
import com.example.budget.R

import com.example.budget.databinding.ActivityProfileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar!!.title = "Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            intent = Intent(this, `LoginActivity`::class.java)
            startActivity(intent)
            finish()

            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, `LoginActivity`::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }
    override fun onBackPressed() {
        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            var user = firebaseAuth.currentUser
            var name = user?.displayName
            var emailAdress = user?.email
            binding.emailAddress.text = emailAdress.toString()
            binding.name.text = name

        }
        else if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            binding.emailAddress.text = GoogleSignIn.getLastSignedInAccount(this)?.email
            binding.name.text = GoogleSignIn.getLastSignedInAccount(this)?.displayName
          //val userEmail = binding.emailAddress.setText(account.email.toString())

        }
        else {
            val intent = Intent(this, `LoginActivity`::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}