package com.example.homework_n1

import com.example.homework_n1.Importance
import java.util.UUID


data class TodoItem(
    val id:             String = UUID.randomUUID().toString(),
    val done:           Boolean = false,
    val text:           String = "",
    val importance: Importance = Importance.MID,
    val deadline:       Long? = null,
    val createdOn:      Long = System.currentTimeMillis(),
    val editedOn:       Long = createdOn,
)
