package br.com.samuel.treinaiapp.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import br.com.samuel.treinaiapp.databinding.FragmentWorkoutDialogBinding
import br.com.samuel.treinaiapp.ui.adapters.WorkoutAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutDialogFragment : DialogFragment() {
  @Inject
  lateinit var workoutRepository: WorkoutRepository

  @Inject
  lateinit var workoutViewModel: WorkoutViewModel
  lateinit var binding: FragmentWorkoutDialogBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_workout_dialog, container, false)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    binding = FragmentWorkoutDialogBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(requireContext())
    val adapter = WorkoutAdapter(emptyList())
    builder.setView(binding.root)
    builder.setPositiveButton("Salvar") { _, _ ->
      val name = binding.editTextNameWorkout.text.toString()
      val description = binding.editTextDescription.text.toString()
      workoutViewModel.insertWorkoutsToLocalDatabase(name, description)
      parentFragmentManager.setFragmentResult("workoutAdded", Bundle())
      dismiss()
    }
    builder.setNegativeButton("Cancelar") { _, _ ->
      dismiss()
    }
    return builder.create()
  }

}