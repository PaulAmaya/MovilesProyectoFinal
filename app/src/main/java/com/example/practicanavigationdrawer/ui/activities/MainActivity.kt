package com.example.practicanavigationdrawer.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicanavigationdrawer.R
import com.example.practicanavigationdrawer.repositories.RetrofitRepository
import com.example.practicanavigationdrawer.utils.SessionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargar el token de SharedPreferences y configurarlo en RetrofitRepository
        val sessionManager = SessionManager(this)
        val token = sessionManager.getAuthToken()
        if (!token.isNullOrEmpty()) {
            RetrofitRepository.setAuthToken(token)
            startActivity(Intent(this, OrdersActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}