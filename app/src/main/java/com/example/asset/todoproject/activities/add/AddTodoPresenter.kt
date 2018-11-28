package com.example.asset.todoproject.activities.add

import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.Todo
import com.google.firebase.database.*


class AddTodoPresenter(override var view: AddTodoContract.View?) : AddTodoContract.Presenter {

    val myDB = FirebaseDatabase.getInstance()
    val ref = myDB.reference.child("todos")

    override fun add(todo: Todo) {
        var list: MutableList<Todo>

        ref.child(todo.userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                view?.onAddError(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                list = p0.getValue() as MutableList<Todo>
                Logger.msg("data " + list)
                list.add(todo)
                ref.child(todo.userId).setValue(list, object: DatabaseReference.CompletionListener{
                    override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                        if (p0 != null) {
                            view?.onAddError(p0.message)
                        } else {
                            view?.onAddSuccess()
                        }
                    }
                })
            }
        })
    }
}