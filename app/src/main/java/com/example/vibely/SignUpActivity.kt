package com.example.vibely

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vibely.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        firebaseAuth = Firebase.auth

        binding.ivArrowBackSignUp.setOnClickListener{
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvSignInSignUp.setOnClickListener {
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        val textEmail = binding.etTextEmailAddressSignUp
        val textPassword = binding.etTextPasswordSignUp
        val textConfirmPassword = binding.etTextConfirmPasswordSignUp

        binding.btnSignUpSignUp.setOnClickListener {
            val email = textEmail.text.toString()
            val password = textPassword.text.toString()
            val confirmPassword = textConfirmPassword.text.toString()

            if (email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                textEmail.error = "This field can't be empty"
                textPassword.error = "This field can't be empty"
                textConfirmPassword.error = "This field can't be empty"
            }
            if (email.isEmpty()) {
                textEmail.error ="This field can't be empty"
            }
            if (password.isEmpty()) {
                textPassword.error = "This field can't be empty"
            }
            if (confirmPassword.isEmpty()) {
                textConfirmPassword.error = "This field can't be empty"
            }

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    createNewUser(email, password)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password didn't match",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun createNewUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                binding.progressBarSignUp.visibility = View.VISIBLE
                if(task.isSuccessful) {
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


}