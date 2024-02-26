package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.databinding.ActivityRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class RegActivity : AppCompatActivity() {
    private lateinit var dbref: CollectionReference
    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        dbref= FirebaseFirestore.getInstance().collection("User")
        binding.Signupbtn.setOnClickListener{

            val confirmPass =binding.editTextTextPassword2.text.toString()

            val user=User(
                name=binding.editTextText.text.toString(),
                email=binding.editTextTextEmailAddress.text.toString(),
                phone=binding.editTextPhone.text.toString(),
                password=binding.editTextTextPassword.text.toString())
            val phnlen= user.phone.length

            if(user.name.isNotEmpty()&& user.email.isNotEmpty() && user.phone.isNotEmpty() && user.password.isNotEmpty())
            {

                for (char in user.name){
                    if (char.isDigit()==true){
                        binding.editTextText.setError("Invalid Username")
                        return@setOnClickListener
                    }
                }

                if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),user.email)){
                    binding.editTextTextEmailAddress.setError("Invalid Email Address")
                    return@setOnClickListener
                }

                if(phnlen!=10){
                    binding.editTextPhone.setError("Invalid Phone Number")
                    return@setOnClickListener
                }

                if (user.password != confirmPass) {
                    binding.editTextTextPassword2.setError("Password is not Matching")
                    return@setOnClickListener
                }
                registerUser(user)

            }
            else{
                Toast.makeText(this, "Empty Fields are not allowed" ,Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun registerUser(user:User){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,user.password)
            .addOnCompleteListener{
                Log.d("Successful Reg", "registerUser: ")
                Toast.makeText(this, "Registered Successfully" ,Toast.LENGTH_SHORT).show()
                clearInputFields()
            }.addOnFailureListener{
                Log.d("Unsuccessful Reg", "registerUser: ")
                Toast.makeText(this, "Register Failed" ,Toast.LENGTH_SHORT).show()
            }
    }
    fun clearInputFields() {
        binding.editTextText.text.clear()
        binding.editTextTextEmailAddress.text.clear()
        binding.editTextPhone.text.clear()
        binding.editTextTextPassword.text.clear()
        binding.editTextTextPassword2.text.clear()
    }

}