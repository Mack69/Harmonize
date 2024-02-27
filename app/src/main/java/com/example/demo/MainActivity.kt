package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.demo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener {

            val email = binding.TextEmail.text.toString()
            val password = binding.TextPassword.text.toString()

            if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)) {
                binding.TextEmail.error = "Invalid Email Address"
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.TextPassword.error = "Password should be min 6 chars"
                return@setOnClickListener
            }
            LoginUser(email, password)
        }

        binding.gotoSignUp.setOnClickListener{
            startActivity(Intent(this,RegActivity::class.java))
        }
    }
    private fun LoginUser(email:String, password :String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Successful Login", "LoginUser: ")
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,UserProfileActivity::class.java))
                    finish()
                    clearInputFields()
                } else {
                    Log.d("Unsuccessful Login", "LoginUser: ", task.exception)
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@MainActivity,UserProfileActivity::class.java)
                .putExtra("user", FirebaseAuth.getInstance().currentUser))
            finish()

        }
    }
    private fun clearInputFields() {
        binding.TextEmail.text.clear()
        binding.TextPassword.text.clear()

    }

}