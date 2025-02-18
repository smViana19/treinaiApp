package br.com.samuel.treinaiapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.databinding.FragmentExerciseDetailsBinding
import br.com.samuel.treinaiapp.ui.adapters.ExerciseDetailsAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.ExerciseDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseDetailsFragment : Fragment() {
  @Inject
  lateinit var exerciseDetailsViewmodel: ExerciseDetailsViewModel
  private lateinit var binding: FragmentExerciseDetailsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_exercise_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val exerciseId = arguments?.getInt("exerciseId", 0) ?: 0
    val adapter = ExerciseDetailsAdapter()
    exerciseDetailsViewmodel.setExerciseId(exerciseId)

    binding = FragmentExerciseDetailsBinding.bind(view)
    binding.recyclerViewSets.layoutManager = LinearLayoutManager(context)

    binding.recyclerViewSets.adapter = adapter

    binding.buttonAddSet.setOnClickListener {
      val newSet = ExerciseSetModel(exerciseId = exerciseId, id = 0)
      adapter.addSet(newSet)
    }

    binding.buttonSaveExerciseDetails.setOnClickListener {
      val updatedSets = adapter.getSets()
      exerciseDetailsViewmodel.saveExerciseSets(updatedSets)
    }

    exerciseDetailsViewmodel.exerciseSets.observe(viewLifecycleOwner) { sets ->
      adapter.updateSets(sets)
    }
    exerciseDetailsViewmodel.loadExerciseSets()
  }
}