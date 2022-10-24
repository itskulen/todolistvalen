package com.example.todolistvalen

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.todolistvalen.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null)
        {
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.desc)
            if(taskItem!!.dueTime != null){
                dueTime = taskItem!!.dueTime!!
                updateTimeButtonText()
            }
        }
        else
        {
            binding.taskTitle.text = "New Task"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()

    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if(taskItem == null)
        {
            val newTask = TaskItem(name,desc,dueTime,null)
            taskViewModel.addTaskItem(newTask)
        }
        else
        {
            taskViewModel.updateTaskItem(taskItem!!.id, name, desc, dueTime)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}
// penjelasan new task sheet.kt //
// menganti dari fragmen menjadi bottom sheet dialog fragmen, //
// mendeklarasikan latenit binding fragmen new task sheet, lalu menginisialisasi binding, yang berguna apabila kita memangil inflate pada fragmen binding, kita harus meleati inflator   //
// menginisialisasi task view model, jadi view model memberikan pengguna dimana activity terpasang dengan main activity//
// pada on create menginisialisasi task view model jadi view model memberikan pengguna dimana activity terpasang dengan main activity//
// pada binding save button mengkonfigurasi set on click listener, lalu membuat function dgn nama save action//
// pada save action akan ditetapkan task view model name yang samadengan nilai dari text yang sudah diedit dari model//
// pada save action akan ditetapkan task view model deskripsi yang samadengan nilai dari text yang sudah diedit dari model//
// memanggil dismiss dimana harusnya membubarkan bottom sheet fragmen //
// pada class new task sheet kita akan mengijinkan untuk melewati var task item//
// membuat kondisi if task item samadengan null, lalu binding title text samadengan edit task, else  lalu binding title text samadengan new task //
// membuat val editable sanadengan editable factory get instance , lalu menambahkann binding nama text samadengan editable neweditable //
// menambahkann binding deskripsi text samadengan editable neweditable //
// pada saveaction membuat kondisi apabila task item samadengan null yang berati kita ada pada new task, lalu membuat new task item yang dimana akan memiliki nilai dari nama, deskripsi dan duetime dan compelted date samadengan null   //
// membuat kondisi else task view model  //
// membuat private value duetime dimana opyional local time dam di inisialisasi sebagai null //
// pada task item apabila tidak samadengan null dan task item due tiem bukan null maka due time samadengan task item due tieme   //
// membuat kondisi update time button //
// pada function updatetimebutton set timepicker buton text samadengan string, jam dan menit, //
// membuat timepickerbutton set on clicklistener, lalu membuat function opentimepicker , apabila due time samadengan null maka due time due time samadengan current time //
// membuat val listener timepickerdialog on time set listener  //
// membuat kondisi duetime samadengan localtime dari jam yg sudah dipilih lalu akan memanggil update time button //
// membuat val dialog samadengan timepicker dialog  //

