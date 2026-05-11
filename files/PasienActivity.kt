package com.example.cekpasien

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cekpasien.adapter.PasienAdapter
import com.example.cekpasien.api.RetrofitClient
import com.example.cekpasien.databinding.ActivityPasienBinding
import kotlinx.coroutines.launch

class PasienActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasienBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = intent.getStringExtra("TOKEN") ?: ""

        binding.rvPasien.layoutManager = LinearLayoutManager(this)

        loadPasien(token)
    }

    private fun loadPasien(token: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getPasien("Bearer $token")
                if (response.isSuccessful) {
                    val data = response.body()
                    if (!data.isNullOrEmpty()) {
                        binding.rvPasien.adapter = PasienAdapter(data)
                    } else {
                        binding.tvEmpty.visibility = View.VISIBLE
                        binding.tvEmpty.text = "Tidak ada data pasien"
                    }
                } else {
                    Toast.makeText(this@PasienActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PasienActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
