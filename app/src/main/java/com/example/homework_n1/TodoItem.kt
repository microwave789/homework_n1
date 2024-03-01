package com.example.homework_n1

data class TodoItem(
    val id: String,
    val isDone: Boolean,
    val createdDate: String,
    val modifiedDate: String? = null
)
