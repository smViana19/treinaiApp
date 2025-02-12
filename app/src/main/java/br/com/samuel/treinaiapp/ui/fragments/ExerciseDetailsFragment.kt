package br.com.samuel.treinaiapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.databinding.FragmentExerciseDetailsBinding
import br.com.samuel.treinaiapp.ui.adapters.ExerciseDetailsAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.ExerciseDetailsViewModel
import javax.inject.Inject

class ExerciseDetailsFragment : Fragment() {
  @Inject
  lateinit var exerciseDetailsViewModel: ExerciseDetailsViewModel
  private lateinit var binding: FragmentExerciseDetailsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_exercise_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentExerciseDetailsBinding.bind(view)
    val adapter = ExerciseDetailsAdapter()
    binding.recyclerViewSets.layoutManager = LinearLayoutManager(context)

    binding.buttonAddSet.setOnClickListener {
      adapter.addSet(ExerciseSetModel())
    }

    val exerciseSets = adapter.getSets()
    binding.buttonSaveExerciseDetails.setOnClickListener {
      exerciseDetailsViewModel.bulkInsertExerciseSets(exerciseSets)
    }
  }
}