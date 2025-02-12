package br.com.samuel.treinaiapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.databinding.FragmentWorkoutDetailsBinding
import br.com.samuel.treinaiapp.ui.adapters.WorkoutDetailsAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.WorkoutDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutDetailsFragment : Fragment() {
  @Inject
  lateinit var workoutDetailsViewModel: WorkoutDetailsViewModel
  private lateinit var binding: FragmentWorkoutDetailsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_workout_details, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val workoutId = arguments?.getInt("workoutId", 0) ?: 0

    binding = FragmentWorkoutDetailsBinding.bind(view)
    workoutDetailsViewModel.getAllExercisesByWorkoutId(workoutId)

    binding.recylcerViewExerciseList.layoutManager = LinearLayoutManager(context)
    val adapter = WorkoutDetailsAdapter(emptyList())
    workoutDetailsViewModel.exercises.observe(viewLifecycleOwner) { exercises ->
      adapter.showExercises(exercises)
    }

    workoutDetailsViewModel.getAllExercisesByWorkoutId(workoutId)
    binding.recylcerViewExerciseList.adapter = adapter
    val bundle = Bundle().apply {
      putInt("workoutId", workoutId)
    }
    binding.textViewAddExercises.setOnClickListener {
      findNavController().navigate(
        resId = R.id.action_workoutDetailsFragment_to_addExerciseFragment,
        args = bundle
      )
    }


  }

}