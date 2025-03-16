package com.example.twaran25.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.data.models.Contact
import com.example.twaran25.data.viewmodel.FirebaseViewModel
import com.example.twaran25.databinding.ActivityContactBinding
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.LeaderBoard
import androidx.core.net.toUri


class ContactActivity : AppCompatActivity() {
    lateinit var contactBinding: ActivityContactBinding
    lateinit var contact: Contact
    private val viewModel: FirebaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        contactBinding = ActivityContactBinding.inflate(layoutInflater) // Inflate here
        setContentView(contactBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        contactBinding.btnSubmit.setOnClickListener {
            Log.d("ContactActivity", "Submit button clicked!")
            saveContactToDatabase()
        }

        navigation()


    }
    private fun navigation(){

        setUpCall(contactBinding.callIcon1,contactBinding.contactNumber1)
        setUpCall(contactBinding.callIcon2,contactBinding.contactNumber2)
        setUpCall(contactBinding.callIcon3,contactBinding.contactNumber3)
        setUpCall(contactBinding.callIcon4,contactBinding.contactNumber4)

        contactBinding.btnContact.setOnClickListener {
            if (javaClass.simpleName != ContactActivity::class.java.simpleName) {
                val intent = Intent(this, ContactActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        contactBinding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != Events::class.java.simpleName) {
                val intent = Intent(this, Events::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        contactBinding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != LeaderBoard::class.java.simpleName) {
                val intent = Intent(this, LeaderBoard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        contactBinding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != Sports::class.java.simpleName) {
                val intent = Intent(this, Sports::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }


    private fun saveContactToDatabase() {
        val name = contactBinding.studentName.text.toString().trim()
        val phoneNo = contactBinding.emailId.text.toString().trim()
        val query = contactBinding.query.text.toString().trim()

        if (name.isEmpty() || phoneNo.isEmpty() || query.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val contact = Contact(name = name, phoneno = phoneNo, queryAndCollege = query)

        // Save to Firebase via ViewModel
        viewModel.contactRequest(contact)

        // Clear input fields after submission
        contactBinding.studentName.text?.clear()
        contactBinding.emailId.text?.clear()
        contactBinding.query.text?.clear()

        Toast.makeText(this, "Submitted successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun setUpCall(callIcon: ImageButton, contactNumber: TextView){
        callIcon.setOnClickListener {
            val phnNum = contactNumber.text.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$phnNum".toUri()
            }
            startActivity(intent)
        }
        contactNumber.setOnClickListener {
            val phnNum = contactNumber.text.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$phnNum".toUri()
            }
            startActivity(intent)
        }
    }

}
