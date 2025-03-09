package com.example.twaran25

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityMainBinding
import com.example.twaran25.events.GameEventsActivity
import com.example.twaran25.games.Sports

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding and layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enable edge-to-edge layout
        enableEdgeToEdge()

        // Set padding to handle system bars (status and navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start animations
        startAnimations()

        binding.guest.setOnClickListener {
            val intent = Intent (this , ContactActivity::class.java)
            startActivity(intent)
        }
        binding.admin.setOnClickListener{
            val intent = Intent (this , GameEventsActivity::class.java)
            startActivity(intent)
        }
    }

    // Start all animations in sequence
    private fun startAnimations() {
        // Hide all views initially
        binding.flyman.visibility = View.INVISIBLE
        binding.bb.visibility = View.INVISIBLE
        binding.mainphoto.visibility = View.INVISIBLE
        binding.leftlight.visibility = View.INVISIBLE
        binding.rightlight.visibility = View.INVISIBLE
        binding.tt1.visibility = View.INVISIBLE
        binding.tt2.visibility = View.INVISIBLE
        binding.logo.visibility = View.INVISIBLE
        binding.button.visibility=View.INVISIBLE

        // Apply glitch effect for photos
        applyGlitchEffect(binding.flyman)
        applyGlitchEffect(binding.bb)
        applyGlitchEffect(binding.mainphoto)

        // After glitch effect, apply light flicker effect (repeatedly)
        Handler(Looper.getMainLooper()).postDelayed({
            applyFlickerEffect(binding.leftlight)
            applyFlickerEffect(binding.rightlight)
        }, 500) // Delay to let glitch effect finish first

        // After light flicker, apply fade-in for text and logo
        Handler(Looper.getMainLooper()).postDelayed({
            // Fade-in for "TWARAN"
            applyFadeInEffect(binding.tt1)
        }, 1500) // Delay to let flicker finish first

        Handler(Looper.getMainLooper()).postDelayed({
            // Fade-in for "INITIATE INCULCATE INSPIRE"
            applyFadeInEffect(binding.tt2)
        }, 1800) // Delay to make sure "TWARAN" fades in first

        // After text, apply fade-in for the logo
        Handler(Looper.getMainLooper()).postDelayed({
            applyFadeInEffect(binding.logo)
        }, 2500) // Delay to let text fade-in finish

        Handler(Looper.getMainLooper()).postDelayed({
            applyFadeInEffect(binding.button)
        }, 2500)

    }

    // Apply glitch effect to the view (photos)
    private fun applyGlitchEffect(photo: View) {
        // Random jitter for position (X and Y axis)
        val randomX = (Math.random() * 10 - 5).toFloat() // Random move in X
        val randomY = (Math.random() * 10 - 5).toFloat() // Random move in Y

        // Apply jitter to position
        val positionX = ObjectAnimator.ofFloat(photo, "translationX", 0f, randomX, 0f)
        val positionY = ObjectAnimator.ofFloat(photo, "translationY", 0f, randomY, 0f)

        // Random scale change
        val randomScale = (Math.random() * 0.1 + 0.9).toFloat() // Scale between 0.9 and 1.0
        val scaleX = ObjectAnimator.ofFloat(photo, "scaleX", 1f, randomScale, 1f)
        val scaleY = ObjectAnimator.ofFloat(photo, "scaleY", 1f, randomScale, 1f)

        // Random opacity change
        val randomAlpha = (Math.random() * 0.2 + 0.8).toFloat() // Alpha between 0.8 and 1.0
        val alpha = ObjectAnimator.ofFloat(photo, "alpha", 1f, randomAlpha, 1f)

        // Animator set to apply the glitch effects
        val glitchEffect = AnimatorSet()
        glitchEffect.playTogether(positionX, positionY, scaleX, scaleY, alpha)
        glitchEffect.duration = 2000 // Duration of glitch effect

        // Start the glitch animation and immediately set visibility to visible
        glitchEffect.start()
        photo.visibility = View.VISIBLE
    }

    // Apply flicker effect to the light
    private fun applyFlickerEffect(light: View) {
        // Fade in for glow effect (only once)
        val fadeIn = ObjectAnimator.ofFloat(light, "alpha", 0.2f, 1f).apply {
            duration = 2000 // Slow glow-in
        }

        // Quick flickers with minimum visibility at 0.2
        val flicker1 = ObjectAnimator.ofFloat(light, "alpha", 1f, 0.4f).apply {
            duration = 500
        }
        val flicker2 = ObjectAnimator.ofFloat(light, "alpha", 0.4f, 1f).apply {
            duration = 500
        }
        val flicker3 = ObjectAnimator.ofFloat(light, "alpha", 1f, 0.3f).apply {
            duration = 650
        }
        val flicker4 = ObjectAnimator.ofFloat(light, "alpha", 0.3f, 1f).apply {
            duration = 650
        }


        val flicker5 = ObjectAnimator.ofFloat(light, "alpha", 1f, 1f).apply {
            duration = 2000
        }

        // Create flicker effect
        val flickerEffect = AnimatorSet().apply {
            playSequentially(flicker1, flicker2, flicker3, flicker4,flicker5)
            startDelay = 100
        }



        // Play fade-in first, then start flickering
        val fullEffect = AnimatorSet().apply {
            playSequentially(fadeIn, flickerEffect)
        }

        // Start the animation
        fullEffect.start()
        light.visibility = View.VISIBLE
    }



    // Apply fade-in effect to a view (for text and logo)
    private fun applyFadeInEffect(view: View) {
        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        fadeIn.duration = 2000 // Duration of fade-in

        // Start the fade-in effect and immediately set visibility to visible
        fadeIn.start()
        view.visibility = View.VISIBLE
    }
}
