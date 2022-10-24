package com.example.todolistvalen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistvalen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecycleViev()
    }

    private fun setRecycleViev()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todolistvalen.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setCompleted(taskItem)
    }
}

//Penjelasan mainactivity.kt//
// pada awalnya mendeklarasikan private latenit variable binding = activitymainbinding, binding samadengan activitymain binding inflate, mengkonfigurasi content view dengan binding root//
// membuat variable taskview model, lalu menginisialisasi menjadi provider//
// membuat binding new task button set on click listener, jadi apabila mengklik button ini akan berpindah ke new task sheet//
// memginisialisasi new task sheet menjadi show//
// membuat task view model yang berguna apabila mengganti nama task view model, akan terganti nama tersebut pada main activity//
// membuat task view model yang berguna apabila mengganti deskripsi task view model, akan terganti deskripsi tersebut pada main activity//
// membuat funtion  setrecylerview, menambahkan val main activity, menambahkan binding todolsitreclyerview menrapkan layout manager dari linear layout manager
//
//
