package br.com.samuel.treinaiapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import br.com.samuel.treinaiapp.databinding.FragmentWorkoutListBinding
import br.com.samuel.treinaiapp.ui.adapters.WorkoutAdapter
import br.com.samuel.treinaiapp.ui.viewmodel.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class WorkoutListFragment : Fragment() {

  @Inject
  lateinit var workoutRepository: WorkoutRepository
  private lateinit var binding: FragmentWorkoutListBinding

  @Inject
  lateinit var workoutViewModel: WorkoutViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_workout_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentWorkoutListBinding.bind(view)

    binding.imageButtonBack.setOnClickListener {
      findNavController().popBackStack()
    }
    binding.floatingButtonAddWorkout.setOnClickListener {
      val dialogFragment = WorkoutDialogFragment()
      dialogFragment.show(requireActivity().supportFragmentManager, "WorkoutDialog")
    }
    binding.recyclerViewListWorkouts.layoutManager = LinearLayoutManager(context)
    val adapter = WorkoutAdapter(emptyList())
    workoutViewModel.workouts.observe(viewLifecycleOwner) { workouts ->
      adapter.updateItems(workouts)
    }
    workoutViewModel.getWorkouts()
    binding.recyclerViewListWorkouts.adapter = adapter
  }
}