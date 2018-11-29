package com.example.asset.todoproject.activities.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.asset.todoproject.R
import com.example.asset.todoproject.activities.add.AddTodoActivity
import com.example.asset.todoproject.helpers.TodoAdapter
import com.example.asset.todoproject.models.Todo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_app.*
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
        rv.adapter = TodoAdapter(this, list)

    }

    override fun onResume() {
        super.onResume()
        presenter.getData(mAuth.currentUser?.uid!!)
    }

    override fun onGetDataSuccess(list: ArrayList<Todo>) {
        this.list.clear()
        this.list.addAll(list)
        rv.adapter.notifyDataSetChanged()
    }

    override fun onGetDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEditSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
