package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.databinding.ActivityHomeBinding
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.databinding.ActivityRegBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userDp.setOnClickListener{
            startActivity(Intent(this,UserProfileActivity::class.java))
        }
    }
}