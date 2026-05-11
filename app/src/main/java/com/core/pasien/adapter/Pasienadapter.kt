package com.core.pasien.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.pasien.databinding.ItemPasienBinding
import com.core.pasien.model.Pasien

class PasienAdapter(private val list: List<Pasien>) :
    RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    inner class PasienViewHolder(private val binding: ItemPasienBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pasien: Pasien) {
            binding.tvNama.text = pasien.name ?: "-"
            binding.tvNoRm.text = "No. RM: ${pasien.no_rm ?: "-"}"
            binding.tvJenisKelamin.text = pasien.jenis_kelamin ?: "-"
            binding.tvTanggalLahir.text = "Tgl Lahir: ${pasien.tanggal_lahir ?: "-"}"
            binding.tvAlamat.text = pasien.alamat ?: "-"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        val binding = ItemPasienBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PasienViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
