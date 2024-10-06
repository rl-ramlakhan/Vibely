package com.example.vibely

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vibely.databinding.ActivitySignInBinding
import java.nio.channels.InterruptedByTimeoutException

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

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

    }
}