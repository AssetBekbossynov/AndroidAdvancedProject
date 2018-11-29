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

    val dateFormat:SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    val mAuht = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val currentDate = Date()

        tag.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tagList)
        priority.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priorityList)

        tag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(parent!!.context, "default tag is not allowed", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    todoTag = null
                    Toast.makeText(parent!!.context, "default tag is not allowed", Toast.LENGTH_SHORT).show()
                }else{
                    todoTag = tagList.get(position)
                    Toast.makeText(parent!!.context, "tag ${tagList.get(position)}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        priority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(parent!!.context, "default priority is not allowed", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    todoPriority = null
                    Toast.makeText(parent!!.context, "default priority is not allowed", Toast.LENGTH_SHORT).show()
                }else{
                    todoPriority = priorityList.get(position)
                    Toast.makeText(parent!!.context, "priority ${priorityList.get(position)}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        add.setOnClickListener {
            if (!description.text.equals("")){
                if (todoPriority != null && todoTag != null){
                    val date = dateFormat.parse("${date_picker.dayOfMonth}/${date_picker.month}/${date_picker.year}")
                    if (currentDate.before(date)){
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
}


