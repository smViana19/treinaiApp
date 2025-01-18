package br.com.samuel.treinaiapp.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.databinding.ActivityAddExerciseBinding
import br.com.samuel.treinaiapp.databinding.BottomSheetAddExercisesBinding
import br.com.samuel.treinaiapp.ui.adapters.ExerciseAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.AddExerciseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAddExerciseBinding
  private val viewModel: AddExerciseViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_exercise)
    binding = ActivityAddExerciseBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.recyclerViewExercises.layoutManager = LinearLayoutManager(this)

    val adapter = ExerciseAdapter(emptyList())
    binding.recyclerViewExercises.adapter = adapter

    binding.recyclerViewExercises.addOnScrollListener(object: RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(!recyclerView.canScrollVertically(1)) {
          viewModel.getExercises()
        }
      }
    })


    binding.buttonCreateExercise.setOnClickListener {
      showBottomSheet()
    }

    viewModel.filteredExercises.observe(this) { exercises ->
      adapter.updateExercises(exercises)
    }
    binding.editTextSearchExercise.addTextChangedListener { text ->
      viewModel.filterExercises(text.toString())

    }
    viewModel.exercises.observe(this) { exercises ->
      exercises?.let {
        adapter.updateExercises(it)
      }
    }
    viewModel.getExercises()


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