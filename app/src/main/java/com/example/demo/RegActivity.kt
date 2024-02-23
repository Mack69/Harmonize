package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.databinding.ActivityRegBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class RegActivity : AppCompatActivity() {
    private lateinit var dbref: CollectionReference
    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbref= FirebaseFirestore.getInstance().collection("User")
        binding.Signupbtn.setOnClickListener{

            val confirmPass =binding.editTextTextPassword2.text.toString()
            val user=User(
                name=binding.editTextText.text.toString(),
                email=binding.editTextTextEmailAddress.text.toString(),
                phone=binding.editTextPhone.text.toString(),
                password=binding.editTextTextPassword.text.toString())


            if(user.name.isNotEmpty()&& user.email.isNotEmpty() && user.phone.isNotEmpty() && user.password.isNotEmpty()){
                if (user.password == confirmPass) {
                    registerUser(user)
                }
                else{
                    Toast.makeText(this, "Password is not matching" ,Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Empty Fields are not allowed" ,Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun registerUser(user:User){

        dbref.add(user)
            .addOnCompleteListener{
                Log.d("Successful Reg", "registerUser: ")
            }.addOnFailureListener{
                Log.d("Unsuccessful Reg", "registerUser: ")

            }
    }
}