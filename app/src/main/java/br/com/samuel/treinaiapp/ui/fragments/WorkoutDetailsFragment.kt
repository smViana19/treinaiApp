package br.com.samuel.treinaiapp.ui.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.databinding.FragmentWorkoutDetailsBinding
import br.com.samuel.treinaiapp.ui.adapters.ExerciseAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior


class WorkoutDetailsFragment : Fragment() {

  private lateinit var binding: FragmentWorkoutDetailsBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_workout_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentWorkoutDetailsBinding.bind(view)
    val adapter = ExerciseAdapter(emptyList())
    binding.textViewAddExercises.setOnClickListener {
      findNavController().navigate(R.id.action_workoutDetailsFragment_to_addExerciseFragment)
    }


  }

}