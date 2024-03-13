package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import com.example.demo.HomeActivity
import com.example.demo.MainActivity
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    //    private lateinit var storageRef : StorageReference
//    private lateinit var dbRef : CollectionReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LogoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.imgBtnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}

//        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryColor)

//        val sharedPreferences: SharedPreferences = this.getSharedPreferences("User", Context.MODE_PRIVATE)
//        val userDataJson = sharedPreferences.getString("user", null)
//        val user = userDataJson?.let { Gson().fromJson(it, User::class.java)}

//        binding.editTextEditName.visibility = View.GONE
//
//        if (user != null) {
//            if (user.picture != null){
//                Log.d("Saved User", "$user")
//                Glide.with(this)
//                    .load(user.picture)
//                    .placeholder(R.drawable.img_user)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)// Add a placeholder drawable
//                    .error(R.drawable.img_user) // Add a drawable for error cases
//                    .into(binding.imgUser)
//            }
//            binding.textUsername.text = user.name
//        }
//
//
//
//
//        storageRef = FirebaseStorage.getInstance().getReference("Images")
//        dbRef = FirebaseFirestore.getInstance().collection("User")
//
//
//        binding.icEditName.setOnClickListener{
//            if(CheckNetwork.isInternetAvailable(this)) {
//                binding.editTextEditName.visibility = View.VISIBLE
//            }else{
//                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        binding.constraintProfile.setOnClickListener{
//            if(binding.editTextEditName.visibility == View.VISIBLE){
//                binding.textUsername.text = binding.editTextEditName.text
//                binding.editTextEditName.visibility = View.GONE
//                if (user != null) {
//                    user.name = binding.editTextEditName.text.toString()
//                    StoreUser.saveData(user, this)
//                    dbRef.document(user.email).update(mapOf("name" to user.name))
//                        .addOnCompleteListener {
//                            Log.d("dbref", "success")
//                        }
//                        .addOnFailureListener {
//                            Log.d("dbref", "failed")
//                        }
//                }
//            }
//        }
//
//        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            binding.imgUser.setImageURI(uri)
//            Log.d("picker", "success")
//
//            if (uri != null) {
//                storageRef.child(user!!.email).putFile(uri)
//                    .addOnSuccessListener { task ->
//                        Log.d("storage", "success")
//                        Log.d("email", "$user")
//
//                        // Retrieve the download URL of the uploaded image
//                        task.metadata!!.reference!!.downloadUrl
//                            .addOnSuccessListener { url ->
//                                val img = url.toString()
//                                user.picture = img
//                                StoreUser.saveData(user, this)
//                                // Update the user's document in Firestore with the image URL
//                                dbRef.document(user.email).update(mapOf("picture" to img))
//                                    .addOnCompleteListener {
//                                        Log.d("dbref", "success")
//                                    }
//                                    .addOnFailureListener {
//                                        Log.d("dbref", "failed")
//                                    }
//                            }
//                    }
//                    .addOnFailureListener {
//                        Log.e("storage", "failed")
//                    }
//            }
//        }
//
//
//        binding.imgUser.setOnClickListener{
//            if(CheckNetwork.isInternetAvailable(this)){
//                pickImage.launch("image/*")
//            }else{
//                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        binding.imgBtnBack.setOnClickListener{
//            super.onBackPressed()
//        }
//    }


//    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
//        Intent()
//        return super.getOnBackInvokedDispatcher()
//    }

