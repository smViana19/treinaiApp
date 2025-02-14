package br.com.samuel.treinaiapp.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.databinding.CardItemBinding

class WorkoutDetailsAdapter(
  private var exercises: List<ExerciseModel>
) : RecyclerView.Adapter<WorkoutDetailsAdapter.ViewHolder>() {
  inner class ViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val exercise = exercises[position]
    holder.binding.textExerciseName.text = exercise.name
    holder.binding.textDescription.text = exercise.description
    holder.binding.cardViewListExercises.setOnClickListener {
      val navController = it.findNavController()
      val bundle = Bundle().apply {
        putInt("exerciseId", exercise.id)
      }
      navController.navigate(
        resId = R.id.action_workoutDetailsFragment_to_exerciseDetailsFragment,
        args = bundle
      )
    }
  }

  override fun getItemCount(): Int {
    return exercises.size
  }

  fun showExercises(newList: List<ExerciseModel>) {
    this.exercises = newList
    notifyDataSetChanged()
  }
}