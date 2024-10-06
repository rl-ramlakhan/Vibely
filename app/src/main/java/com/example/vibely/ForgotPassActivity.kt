package com.example.vibely

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vibely.databinding.ActivityForgotPassBinding

class ForgotPassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        
        binding.ivArrowBackForgotPass.setOnClickListener {
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}