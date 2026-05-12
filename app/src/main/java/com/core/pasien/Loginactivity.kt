package com.core.pasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.core.pasien.api.RetrofitClient
import com.core.pasien.databinding.ActivityLoginBinding
import com.core.pasien.model.LoginRequest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            doLogin(email, password)
        }
    }

    private fun doLogin(email: String, password: String) {
        binding.btnLogin.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.login(LoginRequest(email, password))

                println("CODE = ${response.code()}")
                println("BODY = ${response.body()}")
                println("ERROR = ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    val body = response.body()
                    val token = body?.accessToken
                        ?: body?.token
                        ?: body?.data?.accessToken
                        ?: body?.data?.token

                    if (!token.isNullOrEmpty()) {
                        val intent = Intent(this@LoginActivity, PasienActivity::class.java)
                        intent.putExtra("TOKEN", token)
                        startActivity(intent)
                        finish()
                    } else {
                        println("SEMUA FIELD NULL, body = ${response.body()}")
                        showError("Login gagal: token tidak ditemukan")
                    }
                }
                else {
                    showError("Login gagal: email atau password salah")
                }
                val rawJson = response.body().toString()
                println("FULL BODY = $rawJson")

                // Dan kalau error:
                val errJson = response.errorBody()?.string()
                println("ERROR BODY = $errJson")
            } catch (e: Exception) {
                showError("Koneksi gagal: ${e.message}")
            } finally {
                binding.btnLogin.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}