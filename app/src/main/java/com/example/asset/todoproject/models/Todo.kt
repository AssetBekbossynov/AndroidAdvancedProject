package com.example.asset.todoproject.models

data class Todo (var userId: String,
                 var description: String,
                 var createdDate: String,
                 var planDate: String,
                 var priority: String,
                 var tagId: String){
    constructor(): this("", "", "", "", "", "")
    override fun toString(): String {
        return "Todo(userId='$userId', description='$description', createdDate='$createdDate', planDate='$planDate', priority='$priority', tagId='$tagId')"
    }
}