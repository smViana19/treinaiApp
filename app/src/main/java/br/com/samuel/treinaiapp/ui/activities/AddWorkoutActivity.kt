package br.com.samuel.treinaiapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.samuel.treinaiapp.R

class AddWorkoutActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_add_workout)
//    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//      insets
//    }
  }
}