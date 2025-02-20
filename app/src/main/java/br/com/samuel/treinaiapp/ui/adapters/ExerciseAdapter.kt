package br.com.samuel.treinaiapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.databinding.CardItemBinding


class ExerciseAdapter(
  private var exerciseData: List<ExerciseModel>
) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

  private val favoriteStates = mutableMapOf<Int, Boolean>()

  inner class ViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    val item = exerciseData[position]
    val isFavorite = favoriteStates[position] ?: false
    holder.binding.textExerciseName.text = item.name

//    updateFavoriteIcon(holder.binding.imageButtonFavoriteExercise, isFavorite)

    holder.binding.imageButtonFavoriteExercise.setOnClickListener {
      val newFavoriteState = !(favoriteStates[position] ?: false)
      favoriteStates[position] = newFavoriteState
//      updateFavoriteIcon(holder.binding.imageButtonFavoriteExercise, newFavoriteState)
    }
  }

  override fun getItemCount() = exerciseData.size

  fun updateExercises(newExercises: List<ExerciseModel>) {
    exerciseData = newExercises
    notifyDataSetChanged()
  }

//  private fun updateFavoriteIcon(button: ImageButton, isFavorite: Boolean) {
//
//    if (isFavorite) {
//      button.setImageResource(R.drawable.ic_star_on_24)
//      button.imageTintList = ContextCompat.getColorStateList(button.context, R.color.orange)
//    } else {
//      button.setImageResource(R.drawable.ic_star_off_24)
//      button.imageTintList = ContextCompat.getColorStateList(button.context, R.color.gray_1)
//    }
//  }
}