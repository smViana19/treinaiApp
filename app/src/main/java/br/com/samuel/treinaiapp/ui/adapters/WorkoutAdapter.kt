package br.com.samuel.treinaiapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel

class WorkoutAdapter(private var workoutData: List<WorkoutModel>) :
  RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = itemView.findViewById(R.id.textWorkoutName)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_workout, parent, false)
    return ViewHolder(itemView)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    val item = workoutData[position]
    holder.title.text = item.name
  }

  override fun getItemCount() = workoutData.size

  fun updateItems(workoutChanged: List<WorkoutModel>) {
    workoutData = workoutChanged
    notifyDataSetChanged()
  }
  fun addItem(newWorkout: WorkoutModel) {
    workoutData = workoutData + newWorkout // Adiciona o item na lista
    notifyItemInserted(workoutData.size - 1) // Notifica que um novo item foi inserido
  }
}