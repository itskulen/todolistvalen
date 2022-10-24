package com.example.todolistvalen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel: ViewModel()
{
    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem)
    {
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
    {
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(taskItem: TaskItem)
    {
        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task.completedDate == null)
            task.completedDate = LocalDate.now()
        taskItems.postValue(list)
    }
}

// penjelasan view model.kt//
//membuat variable nama dan deskripsi yang merupakan mutable live data  //
// membuat variable task item yag berupa mutable live data//
// membuat init yang berisi nilai task item sama dengan nutable list of, untuk menginisialisasi list yang dibuat //
// membuat function baru bernama add task item yang menerima tipe task item yang baru, val list samadengan nilai dari task item, lalu menambahkan task ke list lalu post nilai tersebut ke task item //
// membuat function baru bernama update task item, function ini akan menerima nilai nama, deskripsi dan duetime yang telah diupdate atau di edit //
// membuat val task   //
// menginisialisasi task name = name dan task desc = desc  //
// membuat set completed, menambbahkan val list dan task, lalu membuat kondisi apabila task completed samadengan null, maka task item completed samadengan local date baru//


