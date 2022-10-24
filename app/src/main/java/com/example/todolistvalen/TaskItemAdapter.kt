package com.example.todolistvalen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistvalen.databinding.TaskItemCellBinding

class TaskItemAdapter(
    private val taskItems: List<TaskItem>,
    private val clickListener: TaskItemClickListener
): RecyclerView.Adapter<TaskItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(from, parent, false)
        return TaskItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])
    }

    override fun getItemCount(): Int = taskItems.size
}
// penjelasan taskitemadapter.kt //
// adapter task item view holder sudah dibuat dan bisa digunakan , lalu pada task item adapter kita akan menerima list dari task item//
// pada on create view holder membuat variable untuk memanggil yang nilainya samadengan parent context lalu membuat value binding dimana taskitemcellbinding inflate from parent //
// membuat kondisi return taskitemviewholder, lalu memberikan context binding //
// menambahkan private val click listener   //
// pada main activity mengimplementasi task item click listener //
// pada function edit task item baru kita ingin membuaat task sheet baru  //
////
////
