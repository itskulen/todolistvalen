package com.example.todolistvalen

interface TaskItemClickListener
{
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}

// penjelasan interface taskitemclicklistener.kt  //
// membuat function edittaskitem yang berfungsi bila kita mengklik dibagian cardview//
// membuat function completetaskitem yang berfungsi bila kita mengklik dibagian button complete//
// //