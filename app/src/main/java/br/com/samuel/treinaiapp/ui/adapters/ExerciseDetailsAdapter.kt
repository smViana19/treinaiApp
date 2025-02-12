package br.com.samuel.treinaiapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.databinding.CardExerciseDetailBinding

class ExerciseDetailsAdapter : RecyclerView.Adapter<ExerciseDetailsAdapter.ViewHolder>() {
  private val sets = mutableListOf<ExerciseSetModel>()

  inner class ViewHolder(val binding: CardExerciseDetailBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ExerciseDetailsAdapter.ViewHolder {
    val binding =
      CardExerciseDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ExerciseDetailsAdapter.ViewHolder, position: Int) {
    val set = sets[position]
    holder.binding.etReps.setText(set.reps.toString())
    holder.binding.etWeight.setText(set.weight.toString())
  }

  override fun getItemCount(): Int {
    return sets.size
  }

  fun addSet(set: ExerciseSetModel) {
    sets.add(set)
    notifyItemInserted(sets.size - 1)
  }

  fun getSets(): List<ExerciseSetModel> {
    return sets
  }

}