package com.example.asset.todoproject.activities.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.asset.todoproject.R
import com.example.asset.todoproject.activities.add.AddTodoActivity
import com.example.asset.todoproject.helpers.ButtonPressedListener
import com.example.asset.todoproject.helpers.TodoAdapter
import com.example.asset.todoproject.models.Todo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_app.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.title as activityTitle
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AppActivity : AppCompatActivity(), AppContract.View {

    override val presenter: AppContract.Presenter by inject { parametersOf(this) }

    val mAuth = FirebaseAuth.getInstance()

    var list: ArrayList<Todo> = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        add.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = TodoAdapter(this, list, object: ButtonPressedListener{
            override fun onCLick(btn: String, position: Int) {
                if (btn.equals("delete")){
                    presenter.delete(mAuth.currentUser?.uid!!, position)
                }else if (btn.equals("edit")){
                    val intent = Intent(this@AppActivity, AddTodoActivity::class.java)
                    intent.putExtra("edit", true)
                    intent.putExtra("todo", list.get(position))
                    intent.putExtra("position", position)
                    startActivity(intent)
                }
            }
        })

        backbutton.setOnClickListener {
            onBackPressed()
        }

        activityTitle.text = "TODO App"
    }

    override fun onResume() {
        super.onResume()
        presenter.getData(mAuth.currentUser?.uid!!)
    }

    override fun onGetDataSuccess(list: ArrayList<Todo>) {
        if (list.isEmpty()){
            noTodo.visibility = View.VISIBLE
            rv.visibility = View.GONE
        }else{
            noTodo.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
        this.list.clear()
        this.list.addAll(list)
        rv.adapter.notifyDataSetChanged()
    }

    override fun onDeleteSuccess() {
        Toast.makeText(this, "TODO deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
