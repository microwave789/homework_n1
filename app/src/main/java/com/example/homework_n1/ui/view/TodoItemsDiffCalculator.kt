package com.example.homework_n1.ui.view

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_n1.data.model.TodoItem

// Difference Calculator between two lists
class TodoItemsDiffCalculator : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }
}
