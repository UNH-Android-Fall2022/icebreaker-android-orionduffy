package com.example.icebreaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.icebreaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "Icebreaker"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetQuestion.setOnClickListener {
            Log.d(TAG, "Button Get Question was pressed")
            getQuestionsFromFirebase()
        }

        binding.btnSubmit.setOnClickListener {
            Log.d(TAG, "Button Submit was pressed")
        }
    }

    private fun getQuestionsFromFirebase () {
        Log.d(TAG, "Fetching questions from database...")
    }
}