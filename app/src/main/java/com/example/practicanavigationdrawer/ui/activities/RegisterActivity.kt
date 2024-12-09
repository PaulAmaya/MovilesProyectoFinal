package com.example.practicanavigationdrawer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicanavigationdrawer.databinding.ActivityRegisterBinding

import com.example.practicanavigationdrawer.repositories.RegisterRepository
import com.example.practicanavigationdrawer.ui.activities.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoleSpinner()
        setupRegisterButton()
    }

    private fun setupRoleSpinner() {
        val roles = arrayOf("Cliente", "Driver")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRole.adapter = adapter
    }

    private fun setupRegisterButton() {
        binding.buttonRegisterUser.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val role = binding.spinnerRole.selectedItemPosition + 1 // Cliente = 1, Driver = 2

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RegisterRepository.registerUser(
                name = name,
                email = email,
                password = password,
                role = role,
                onSuccess = {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                onError = { errorMessage ->
                    Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
