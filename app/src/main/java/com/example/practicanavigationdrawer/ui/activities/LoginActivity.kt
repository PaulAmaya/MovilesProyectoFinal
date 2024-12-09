package com.example.practicanavigationdrawer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicanavigationdrawer.databinding.ActivityLoginBinding
import com.example.practicanavigationdrawer.ui.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LoginActivity", "onCreate called")

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            Log.d("LoginActivity", "Login button clicked with email: $email")
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.login(this, email, password)
                observeLogin()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeLogin() {
        loginViewModel.loginResponse.observe(this) {
            Log.d("LoginActivity", "Login response received")
            loginViewModel.getUserDetails()
            observeUserDetails()
        }

        loginViewModel.error.observe(this) {
            Log.e("LoginActivity", "Login error: $it")
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeUserDetails() {
        loginViewModel.userResponse.observe(this) { user ->
            Log.d("LoginActivity", "User details received: ${user.profile.role}")
            when (user.profile.role) {
                //testdriver2@test.com
                //test!123
                1 -> startActivity(Intent(this, ClientActivity::class.java))
                2 -> startActivity(Intent(this, OrdersActivity::class.java))
                else -> Toast.makeText(this, "Rol desconocido", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        loginViewModel.error.observe(this) {
            Log.e("LoginActivity", "User details error: $it")
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        }
    }
}