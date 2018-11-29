package com.example.asset.todoproject.activities.main

import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.Todo
import com.google.firebase.database.*

class AppPresenter(override var view: AppContract.View?) : AppContract.Presenter {

    val ref = FirebaseDatabase.getInstance().reference.child("todos")

    lateinit var list : List<Todo>

    var value: ArrayList<Todo>? = null

    override fun getData(userId: String) {
        ref.child(userId).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                view?.onError(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                Logger.msg("todos " + p0.getValue())

                if (p0.getValue() == null){
                    value = ArrayList<Todo>()
                }else{
                    val t = object : GenericTypeIndicator<ArrayList<Todo>>() {}
                    value = p0.getValue(t)
                }
                value!!.get(0).
                view?.onGetDataSuccess(value!!)
            }

        })
    }

    override fun delete(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun edit(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}