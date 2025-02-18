package br.com.samuel.treinaiapp.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.databinding.CardExerciseDetailBinding

class ExerciseDetailsAdapter : RecyclerView.Adapter<ExerciseDetailsAdapter.ViewHolder>() {
  private var sets = mutableListOf<ExerciseSetModel>()

  inner class ViewHolder(val binding: CardExerciseDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var weightWatcher: TextWatcher? = null
    var repsWatcher: TextWatcher? = null
  }

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

    val adapterPosition = holder.adapterPosition
    holder.binding.etReps.removeTextChangedListener(holder.repsWatcher)
    holder.binding.etWeight.removeTextChangedListener(holder.weightWatcher)

    holder.binding.etReps.setText(set.reps.toString())
    holder.binding.etWeight.setText(set.weight.toString())

    val repsWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
      override fun afterTextChanged(s: Editable?) {
        val newReps = s.toString().toIntOrNull() ?: 0
        if (sets[adapterPosition].reps != newReps) {
          sets[adapterPosition].reps = newReps
        }
      }
    }

    val weightWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
      override fun afterTextChanged(s: Editable?) {
        val newWeight = s.toString().toDoubleOrNull() ?: 0.0
        if (sets[adapterPosition].weight != newWeight) {
          sets[adapterPosition].weight = newWeight
        }
      }
    }

    holder.repsWatcher = repsWatcher
    holder.weightWatcher = weightWatcher

    holder.binding.etReps.addTextChangedListener(repsWatcher)
    holder.binding.etWeight.addTextChangedListener(weightWatcher)
  }

  override fun getItemCount(): Int {
    return sets.size
  }

  fun addSet(set: ExerciseSetModel) {
    sets.add(set)
    notifyItemInserted(sets.size - 1)
  }

  fun getSets(): List<ExerciseSetModel> = sets

  fun updateSets(newSets: List<ExerciseSetModel>) {
    sets.clear()
    sets.addAll(newSets)
    notifyDataSetChanged()
  }

  fun removeSets() {}

}