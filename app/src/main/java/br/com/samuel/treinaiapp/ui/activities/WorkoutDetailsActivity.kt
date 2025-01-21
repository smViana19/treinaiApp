package br.com.samuel.treinaiapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.ui.adapters.WorkoutAdapter

class WorkoutDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_workout_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.recylcerViewExerciseList)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        val items = listOf(
//            "Supino inclinado com halteres",
//            "Supino reto com barra",
//            "Remada curvada com barra",
//            "Agachamento livre",
//            "Desenvolvimento com halteres",
//            "Rosca direta com barra",
//            "Tríceps testa com barra",
//            "Elevação lateral com halteres",
//            "Cadeira extensora",
//            "Leg press 45°",
//            "Puxada frontal na polia",
//            "Stiff com barra",
//            "Flexão de braços",
//            "Abdominal supra no solo",
//            "Prancha abdominal",
//            "Crucifixo inclinado com halteres"
//        )
//        val adapter = WorkoutAdapter(items)
//        recyclerView.adapter = adapter
    }

}