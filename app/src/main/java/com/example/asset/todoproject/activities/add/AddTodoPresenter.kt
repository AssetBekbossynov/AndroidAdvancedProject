package com.example.asset.todoproject.activities.add

import com.example.asset.todoproject.helpers.Logger
import com.example.asset.todoproject.models.Todo
import com.google.firebase.database.*


class AddTodoPresenter(override var view: AddTodoContract.View?) : AddTodoContract.Presenter {

    val myDB = FirebaseDatabase.getInstance()
    val ref = myDB.reference.child("todos")

    override fun add(todo: Todo) {
        var list: MutableList<Todo>?

        ref.child(todo.userId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                view?.onAddError(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                Logger.msg("data " + p0.getValue())
                if (p0.getValue() == null){
                    list = ArrayList<Todo>()
                }else{
                    list = p0.getValue() as MutableList<Todo>
                }
                list!!.add(todo)
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

    override fun edit(todo: Todo, position: Int) {
        if (position == -1){
            view?.onEditError("invalid TODO")
        }else{
            ref.child(todo.userId).child(position.toString()).removeValue()
            ref.child(todo.userId).child(position.toString()).setValue(todo, object : DatabaseReference.CompletionListener{
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    view?.onEditSuccess()
                }

            })
        }
    }
}