package br.com.samuel.treinaiapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel

class WorkoutAdapter(private var workoutData: List<WorkoutModel>) :
  RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = itemView.findViewById(R.id.textWorkoutName)
    val card: CardView = itemView.findViewById(R.id.cardViewListWorkouts)
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
    holder.card.id = item.id

    holder.card.setOnClickListener {
      println("HOLDER ID: ${holder.card.id}")
      println("ITEM ID: ${item.id}")
      val navController = it.findNavController()
      navController.navigate(R.id.action_workoutListFragment_to_workoutDetailsFragment)
    }
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