package com.example.twaran25

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.databinding.ActivityMainBinding
import com.example.twaran25.games.AdminMatches
import com.example.twaran25.games.Sports
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Enable edge-to-edge layout
        enableEdgeToEdge()

        // Handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start animations
        startAnimations()

        // Login Button Click Listener
        binding.btnSubmit.setOnClickListener {
            val email = binding.emailId.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.guest.setOnClickListener {
            startActivity(Intent(this, Sports::class.java))
        }

        binding.btnAdmin.setOnClickListener {
            binding.cardLogin.visibility = View.VISIBLE
        }

        binding.backButton.setOnClickListener {
            binding.cardLogin.visibility = View.GONE
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Done", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminMatches::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Start all animations in sequence
    private fun startAnimations() {
        val viewsToHide = listOf(
            binding.flyman, binding.bb, binding.mainphoto, binding.leftlight, binding.rightlight,
            binding.tt1, binding.tt2, binding.logo, binding.button
        )
        viewsToHide.forEach { it.visibility = View.INVISIBLE }

        // Apply glitch effect for images
        listOf(binding.flyman, binding.bb, binding.mainphoto).forEach { applyGlitchEffect(it) }

        // After glitch effect, apply flicker effect
        Handler(Looper.getMainLooper()).postDelayed({
            applyFlickerEffect(binding.leftlight)
            applyFlickerEffect(binding.rightlight)
        }, 500)

        // Fade-in animations for text and logo
        Handler(Looper.getMainLooper()).postDelayed({ applyFadeInEffect(binding.tt1) }, 1500)
        Handler(Looper.getMainLooper()).postDelayed({ applyFadeInEffect(binding.tt2) }, 1800)
        Handler(Looper.getMainLooper()).postDelayed({ applyFadeInEffect(binding.logo) }, 2500)
        Handler(Looper.getMainLooper()).postDelayed({ applyFadeInEffect(binding.button) }, 2500)
    }

    // Apply glitch effect to a view (photos)
    private fun applyGlitchEffect(photo: View) {
        val randomX = (Math.random() * 10 - 5).toFloat()
        val randomY = (Math.random() * 10 - 5).toFloat()
        val positionX = ObjectAnimator.ofFloat(photo, "translationX", 0f, randomX, 0f)
        val positionY = ObjectAnimator.ofFloat(photo, "translationY", 0f, randomY, 0f)
        val randomScale = (Math.random() * 0.1 + 0.9).toFloat()
        val scaleX = ObjectAnimator.ofFloat(photo, "scaleX", 1f, randomScale, 1f)
        val scaleY = ObjectAnimator.ofFloat(photo, "scaleY", 1f, randomScale, 1f)
        val randomAlpha = (Math.random() * 0.2 + 0.8).toFloat()
        val alpha = ObjectAnimator.ofFloat(photo, "alpha", 1f, randomAlpha, 1f)

        AnimatorSet().apply {
            playTogether(positionX, positionY, scaleX, scaleY, alpha)
            duration = 2000
            start()
        }
        photo.visibility = View.VISIBLE
    }

    // Apply flicker effect to the light
    private fun applyFlickerEffect(light: View) {
        val fadeIn = ObjectAnimator.ofFloat(light, "alpha", 0.2f, 1f).apply { duration = 2000 }
        val flickerSequence = listOf(
            ObjectAnimator.ofFloat(light, "alpha", 1f, 0.4f).apply { duration = 500 },
            ObjectAnimator.ofFloat(light, "alpha", 0.4f, 1f).apply { duration = 500 },
            ObjectAnimator.ofFloat(light, "alpha", 1f, 0.3f).apply { duration = 650 },
            ObjectAnimator.ofFloat(light, "alpha", 0.3f, 1f).apply { duration = 650 },
            ObjectAnimator.ofFloat(light, "alpha", 1f, 1f).apply { duration = 2000 }
        )

        AnimatorSet().apply {
            playSequentially(fadeIn, AnimatorSet().apply { playSequentially(flickerSequence) })
            start()
        }
        light.visibility = View.VISIBLE
    }

    // Apply fade-in effect to a view (for text and logo)
    private fun applyFadeInEffect(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 2000
            start()
        }
        view.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, AdminMatches::class.java))
            finish()
        }
    }
}
