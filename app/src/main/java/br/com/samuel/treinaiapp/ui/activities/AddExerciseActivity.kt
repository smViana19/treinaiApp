package br.com.samuel.treinaiapp.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.databinding.ActivityAddExerciseBinding
import br.com.samuel.treinaiapp.databinding.BottomSheetAddExercisesBinding
import br.com.samuel.treinaiapp.databinding.CardItemBinding
import br.com.samuel.treinaiapp.ui.adapters.ExerciseAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddExerciseActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAddExerciseBinding
  private lateinit var cardBinding: CardItemBinding
  private var isFavorite = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_exercise)
    binding = ActivityAddExerciseBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.recyclerViewExercises.layoutManager = LinearLayoutManager(this)

    val items = listOf(
      "Supino inclinado com halteres",
      "Supino reto com barra",
      "Remada curvada com barra",
      "Agachamento livre",
      "Desenvolvimento com halteres",
      "Rosca direta com barra",
      "Tríceps testa com barra",
      "Elevação lateral com halteres",
      "Cadeira extensora",
      "Leg press 45°",
      "Puxada frontal na polia",
      "Stiff com barra",
      "Flexão de braços",
      "Abdominal supra no solo",
      "Prancha abdominal",
      "Crucifixo inclinado com halteres"
    )
    val adapter = ExerciseAdapter(items)
    binding.recyclerViewExercises.adapter = adapter

    binding.buttonCreateExercise.setOnClickListener {
      showBottomSheet()
    }
  }



  private fun showBottomSheet() {
    val bottomSheetDialog = BottomSheetDialog(this)
    val bottomSheetBinding = BottomSheetAddExercisesBinding.inflate(LayoutInflater.from(this))
    bottomSheetDialog.setContentView(bottomSheetBinding.root)
    setupCounter(
      bottomSheetBinding.incrementRepetition,
      bottomSheetBinding.decrementRepetition,
      bottomSheetBinding.editTextRepetition
    )
    setupCounter(
      bottomSheetBinding.incrementSets,
      bottomSheetBinding.decrementSets,
      bottomSheetBinding.editTextSets
    )
    saveExercise(bottomSheetBinding, bottomSheetDialog)
    bottomSheetDialog.show()
  }

  private fun setupCounter(
    incrementButton: View,
    decrementButton: View,
    editText: EditText,
    minValue: Int = 1
  ) {
    incrementButton.setOnClickListener {
      val currentValue = editText.text.toString().toInt()
      editText.setText((currentValue + 1).toString())
    }
    decrementButton.setOnClickListener {
      val currentValue = editText.text.toString().toInt()
      if (currentValue > minValue) {
        editText.setText((currentValue - 1).toString())
      }
    }
  }

  private fun saveExercise(
    bottomSheetBinding: BottomSheetAddExercisesBinding,
    bottomSheetDialog: BottomSheetDialog
  ) {
    bottomSheetBinding.buttonSaveExercise.setOnClickListener {
      val exerciseName = bottomSheetBinding.editTextCreateExercise.text.toString()
      val reps = bottomSheetBinding.editTextRepetition.text.toString().toInt()
      val sets = bottomSheetBinding.editTextSets.text.toString().toInt()

      if (exerciseName.isNotEmpty() && reps > 0 && sets > 0) {
        Toast.makeText(
          this,
          "Exercício $exerciseName ($sets x $reps) adicionado!",
          Toast.LENGTH_SHORT
        ).show()
        bottomSheetDialog.dismiss()
      } else {
        Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
      }
    }
  }

}