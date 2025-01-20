package br.com.samuel.treinaiapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.databinding.FragmentWorkoutListBinding
import br.com.samuel.treinaiapp.ui.adapters.WorkoutAdapter

class WorkoutListFragment : Fragment() {
  private lateinit var binding: FragmentWorkoutListBinding
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


    binding.recyclerViewListWorkouts.layoutManager = LinearLayoutManager(context)
    val items = listOf("Treino A", "Treino B", "Treino C", "Treino D")
    val adapter = WorkoutAdapter(items)
    binding.recyclerViewListWorkouts.adapter = adapter



  }
}