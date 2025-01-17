package br.com.samuel.treinaiapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.ui.adapters.WorkoutAdapter

class WorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = listOf("Treino 1", "Treino 2", "Treino 3", "Treino 4")
        val adapter = WorkoutAdapter(items)
        recyclerView.adapter = adapter
    }
}