package com.example.magicacompleto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.magicacompleto.databinding.ActivityMostraHistoriasBinding
import com.example.magicacompleto.view.adapters.HistoriaAdapter
import com.example.magicacompleto.viewmodels.MainViewModel

class MostraHistorias : AppCompatActivity() {

        private lateinit var binding: ActivityMostraHistoriasBinding
        private lateinit var viewModel: MainViewModel
        private lateinit var adapter: HistoriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostraHistoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddUpdate.setOnClickListener {
            startActivity(Intent(this@MostraHistorias, Login::class.java))
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupRecyclerView()

        viewModel.obtenerHistoria()

        viewModel.listaComment.observe(this, Observer { it ->
            adapter.listaComment = it
            adapter.notifyDataSetChanged()
        })

    }

    private fun setupRecyclerView() {
        binding.rvComment.layoutManager = GridLayoutManager(this, 2)
        adapter = HistoriaAdapter(this, arrayListOf())
        binding.rvComment.adapter = adapter
    }

    private fun ocultarTeclado() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}


