package com.example.demo

import android.content.Intent
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
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbref= FirebaseFirestore.getInstance().collection("User")
        firebaseAuth=FirebaseAuth.getInstance()

        binding.Signupbtn.setOnClickListener{

            val confirmPass =binding.editTextTextPassword2.text.toString()
            val user=User(
                name=binding.editTextText.text.toString(),
                email=binding.editTextTextEmailAddress.text.toString(),
                phone=binding.editTextPhone.text.toString(),
                password=binding.editTextTextPassword.text.toString())
            val phonelength= user.phone.length

            if(user.name.isNotEmpty()&& user.email.isNotEmpty() && user.phone.isNotEmpty() && user.password.isNotEmpty())
            {

                for (char in user.name){
                    if (char.isDigit()){
                        binding.editTextText.error = "Invalid Username"
                        return@setOnClickListener
                    }
                }

                if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),user.email)){
                    binding.editTextTextEmailAddress.error = "Invalid Email Address"
                    return@setOnClickListener
                }

                if(phonelength!=10){
                    binding.editTextPhone.error = "Invalid Phone Number"
                    return@setOnClickListener
                }

                if(user.password.length <6){
                    binding.editTextTextPassword.error = "Password should be min 6 chars"
                    return@setOnClickListener
                }

                if (user.password != confirmPass) {
                    binding.editTextTextPassword2.error = "Password is not Matching"
                    return@setOnClickListener
                }
                registerUser(user)
                startActivity(Intent(this,MainActivity::class.java))

            }
            else{
                Toast.makeText(this, "Empty Fields are not allowed" ,Toast.LENGTH_SHORT).show()

            }
        }
    }

//    private fun registerUser(user:User){
//        firebaseAuth.createUserWithEmailAndPassword(user.email,user.password)
//            .addOnCompleteListener{
//                Log.d("Successful Reg", "registerUser: ")
//                Toast.makeText(this, "Registered Successfully" ,Toast.LENGTH_SHORT).show()
//                clearInputFields()
//            }.addOnFailureListener{
//                Log.d("Unsuccessful Reg", "registerUser: ")
//                Toast.makeText(this, "Register Failed" ,Toast.LENGTH_SHORT).show()
//            }
//    }
private fun registerUser(user: User) {
    val data = mapOf(
        "email" to user.email,
        "name" to user.name,
        "phone" to user.phone
    )
    dbref.add(data)
    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Successful Reg", "registerUser: ")
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                clearInputFields()
            } else {
                Log.d("Unsuccessful Reg", "registerUser: ", task.exception)
                Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show()
            }
        }
}

    private fun clearInputFields() {
        binding.editTextText.text.clear()
        binding.editTextTextEmailAddress.text.clear()
        binding.editTextPhone.text.clear()
        binding.editTextTextPassword.text.clear()
        binding.editTextTextPassword2.text.clear()
    }

}