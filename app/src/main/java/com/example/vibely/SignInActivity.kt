package com.example.vibely

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vibely.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.nio.channels.InterruptedByTimeoutException

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        firebaseAuth = Firebase.auth

        binding.tvCreateAccountSignIn.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvForgotPasswordSignIn.setOnClickListener {
            val intent = Intent(applicationContext, ForgotPassActivity::class.java)
            startActivity(intent)
            finish()
        }

        val textEmail = binding.etTextEmailAddressSignIn
        val textPassword = binding.etTextPasswordSignIn

        binding.btnSignInSignIn.setOnClickListener {
            val email = textEmail.text.toString()
            val password = textPassword.text.toString()

            if (email.isEmpty() && password.isEmpty()) {
                textEmail.error ="This field can't be empty"
                textPassword.error = "This field can't be empty"
            }
            if (email.isEmpty()) {
                textEmail.error = "This field can't be empty"
            }
            if (password.isEmpty()) {
                textPassword.error = "This field can't be empty"
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            }
        }

    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding.progressBarSignIn.visibility = View.VISIBLE
                if (task.isSuccessful) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}