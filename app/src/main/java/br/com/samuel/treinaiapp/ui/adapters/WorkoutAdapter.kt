package br.com.samuel.treinaiapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.samuel.treinaiapp.R
class WorkoutAdapter(private val workoutData: List<String>) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val title: TextView = itemView.findViewById(R.id.textCardTitle)
      val description: TextView = itemView.findViewById(R.id.textCardDescription)

  }
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
    return ViewHolder(itemView)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    val item = workoutData[position]
    holder.title.text = item
    holder.description.text = "Descrição para $item"
  }

  override fun getItemCount() = workoutData.size


}