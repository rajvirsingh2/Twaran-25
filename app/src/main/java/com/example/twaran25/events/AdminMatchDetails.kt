package com.example.twaran25.events

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twaran25.DataSource
import com.example.twaran25.R
import com.example.twaran25.databinding.ActivityAdminMatchDetailsBinding

class AdminMatchDetails : AppCompatActivity() {
    lateinit var binding: ActivityAdminMatchDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminMatchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.eventsRecycler.layoutManager = LinearLayoutManager(this)
        binding.eventsRecycler.adapter = AdminMatchesAdapter(DataSource.events)


        binding.backarrow.setOnClickListener {
            finish()
        }
    }
}