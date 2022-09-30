package com.example.icebreaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.icebreaker.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "Icebreaker"
    private val db = Firebase.firestore
    private var questionBank: MutableList<Question> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getQuestionsFromFirebase()

        binding.btnGetQuestion.setOnClickListener {
            Log.d(TAG, "Button Get Question was pressed")
        }

        binding.btnSubmit.setOnClickListener {
            Log.d(TAG, "Button Submit was pressed")
        }
    }

    private fun getQuestionsFromFirebase () {
        Log.d(TAG, "Fetching questions from database...")
        db.collection("questions")
            .get()
            .addOnSuccessListener { collection ->
                for (document in collection) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                    val question = document.toObject(Question::class.java)
                    Log.d(TAG, question.text)
                    questionBank.add(question)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun writeUserToFirebase() {
        val firstName = binding.txtFirstName
        val lastName = binding.txtLastName
        val preferredName = binding.txtPreferredName
        val answer = binding.txtAnswer

        val user = hashMapOf(
            "firstName" to firstName.text.toString(),
            "lastName" to lastName.text.toString(),
            "preferredName" to preferredName.text.toString(),
            "answer" to answer.text.toString()
        )

        firstName.setText("")
        lastName.setText("")
        preferredName.setText("")
        answer.setText("")
    }
}