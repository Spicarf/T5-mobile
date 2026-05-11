package com.core.pasien

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.pasien.adapter.PasienAdapter
import com.core.pasien.api.RetrofitClient
import com.core.pasien.databinding.ActivityPasienBinding
import com.core.pasien.model.Pasien
import kotlinx.coroutines.launch

class PasienActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasienBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPasien.layoutManager =
            LinearLayoutManager(this)

        loadPasien()
    }

    private fun loadPasien() {

        binding.progressBar.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.GONE

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitClient.instance.getPasien()
                println(response.body())
                println(response.errorBody()?.string())
                if (response.isSuccessful) {

                    val data =
                        response.body()?.data

                    if (data != null && data.isNotEmpty()) {

                        binding.rvPasien.adapter =
                            PasienAdapter(data)

                    } else {

                        binding.tvEmpty.visibility =
                            View.VISIBLE

                        binding.tvEmpty.text =
                            "Tidak ada data pasien"
                    }

                } else {

                    Toast.makeText(
                        this@PasienActivity,
                        "Gagal memuat data",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@PasienActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            binding.progressBar.visibility =
                View.GONE
        }
    }
}