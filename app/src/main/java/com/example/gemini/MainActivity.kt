package com.example.gemini



import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val eTPrompt = findViewById<EditText>(R.id.eTPromt)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tVResult: TextView = findViewById<TextView>(R.id.tVResult)

        btnSubmit.setOnClickListener{
            val prompt = eTPrompt.text.toString()

            val generativeModel = GenerativeModel(
                // For text-only input, use the gemini-pro model
                modelName = "gemini-pro",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "AIzaSyC3WRQeiZ-0WLWhWQ22sYoveQz0LNHIp3g"
            )

            runBlocking {
                val response = generativeModel.generateContent(prompt)
                tVResult.text = response.text
            }
        }
    }
}
