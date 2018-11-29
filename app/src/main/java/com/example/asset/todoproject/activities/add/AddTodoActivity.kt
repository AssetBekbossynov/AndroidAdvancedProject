package com.example.asset.todoproject.activities.add

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.asset.todoproject.R
import kotlinx.android.synthetic.main.activity_add_todo.*
import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.Todo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.title as activityTitle
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class AddTodoActivity : AppCompatActivity(), AddTodoContract.View {

    override val presenter: AddTodoContract.Presenter by inject { parametersOf(this) }

    val tagList: ArrayList<String> = arrayListOf("Choose Tag", "Home", "Work", "School")
    val priorityList: ArrayList<String> = arrayListOf("Choose priority", "High", "Medium", "Low")

    var todoPriority: String? = null
    var todoTag: String? = null

    var todo: Todo? = null

    val dateFormat:SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    val mAuht = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val currentDate = Date()

        if (intent.hasExtra("edit")){
            activityTitle.text = "Edit TODO"
            todo = intent.getParcelableExtra("todo")
            description.setText(todo!!.description)
            for (i in 0 until tagList.size){
                if (todo!!.tagId.equals(tagList.get(i))){
                    tag.setSelection(i)
                }
            }
            for (i in 0 until priorityList.size){
                if (todo!!.priority.equals(priorityList.get(i))){
                    priority.setSelection(i, false)
                }
            }
            val date = Date(todo!!.planDate)
            val calendar = Calendar.getInstance()
            calendar.time = date

            date_picker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

            add.setText("Edit")
        }else{
            activityTitle.text = "Add TODO"
        }

        backbutton.setOnClickListener {
            finish()
        }

        tag.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tagList)
        priority.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priorityList)

        tag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(parent!!.context, "default tag is not allowed", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    todoTag = null
//                    Toast.makeText(parent!!.context, "default tag is not allowed", Toast.LENGTH_SHORT).show()
                }else{
                    todoTag = tagList.get(position)
//                    Toast.makeText(parent!!.context, "tag ${tagList.get(position)}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        priority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(parent!!.context, "default priority is not allowed", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    todoPriority = null
//                    Toast.makeText(parent!!.context, "default priority is not allowed", Toast.LENGTH_SHORT).show()
                }else{
                    todoPriority = priorityList.get(position)
//                    Toast.makeText(parent!!.context, "priority ${priorityList.get(position)}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        add.setOnClickListener {

            if (!description.text.equals("")){
                if (todoPriority != null && todoTag != null){
                    val date = dateFormat.parse("${date_picker.dayOfMonth}/${date_picker.month}/${date_picker.year}")
                    if (intent.hasExtra("edit")){
                        val editTodo = Todo(mAuht.currentUser?.uid!!,
                            description.text.toString(), currentDate.toString(),
                            date.toString(), todoPriority!!, todoTag!!)
                        presenter.edit(editTodo, intent.getIntExtra("position", -1))
                    }else if (currentDate.before(date)){
                        Toast.makeText(this, "date should be after current date", Toast.LENGTH_SHORT).show()
                    }else{
                        var todo = Todo(mAuht.currentUser?.uid!!,
                                description.text.toString(), currentDate.toString(),
                                date.toString(), todoPriority!!, todoTag!!)
                        presenter.add(todo)
                    }
                } else{
                    Toast.makeText(this, "priority tag choose", Toast.LENGTH_SHORT).show()
                }
            } else{
                description.requestFocus()
                Toast.makeText(this, "Fill description", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAddSuccess() {
        finish()
        Logger.msg("successfully added")
    }

    override fun onAddError(msg: String) {
        Toast.makeText(this, "Could not add TODO", Toast.LENGTH_SHORT).show()
    }

    override fun onEditSuccess() {
        finish()
    }

    override fun onEditError(msg: String) {
        Toast.makeText(this, "Could not edit TODO", Toast.LENGTH_SHORT).show()
    }
}


