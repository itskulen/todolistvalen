package com.example.todolistvalen

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistvalen.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{
    @RequiresApi(Build.VERSION_CODES.O)
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTaskItem(taskItem: TaskItem)
    {
        binding.name.text = taskItem.name

        if (taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTime != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime)
        else
            binding.dueTime.text = ""
    }
}

//penjelasan taskitemviewholder.kt //
// Taskitemviewholder adalah tipe recyclerviewholder, lalu membuat private val context dan binding  //
// membuat value time format dimana datetime formatter memiliki pattren jam dan menit//
// membuat kondisi apabila task item duetime adalah tidak samadengan null maka set binding duetime samadengan timeformat, dan apabila tidak samadengan null maka akan diset sebagai string kosong //
// membuat kondisi apabila taskitemcompleted maka set paintflags dari nama  //
// membuat kondisi apabila taskitemcompleted maka set paintflags dari duetime  //
// membuat private val click listener, lalu membuat kondisi complete button set on click listener lalu akan memanggil interface  //  //
// membuat kondisi taskcellcontainer apabila mengklik pada cardview langsung menampilkan menu edit //

