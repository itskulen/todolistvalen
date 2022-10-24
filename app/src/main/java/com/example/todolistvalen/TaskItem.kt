package com.example.todolistvalen


import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskItem(
        var name: String,
        var desc: String,
        var dueTime: LocalTime?,
        var completedDate: LocalDate?,
        var id: UUID = UUID.randomUUID()
)
{
        fun isCompleted() = completedDate != null
        fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
        fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

        private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
        private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}
// penjelasan taskitem.kt//
// class taskitem berisi variable nama dan deskripsi yang berupa string lalu duetime beua local time dan completed date berupa local date pada localtime dan localdatye diberi tand tanya yng artinya nilai tersebut opsional sehingga bia bernilai null  //
// membuat variable task item yag berupa mutable live data//
// membuat function is completed, dimana samadengan completeddate dimana tidaksamadengan null //
// membuat function imageresource, membuat fungsi apabila iscompleted maka akan return drawable checked atau unchecked//
// membuat function imagecolor berisi context, //
// membuat function purple yang dimana akan menerima coontext getcolor   //
// membuat function black yang dimana akan menerima coontext getcolor   //
