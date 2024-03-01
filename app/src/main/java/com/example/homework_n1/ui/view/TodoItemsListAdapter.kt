package com.example.homework_n1.ui.view

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.recyclerview.widget.ListAdapter
import com.example.homework_n1.R
import com.example.homework_n1.data.model.TodoItem
import com.example.homework_n1.ui.stateholders.TodoItemsViewModel

// Adapter for the RecyclerView
@RequiresApi(Build.VERSION_CODES.M)
class TodoItemsListAdapter(
    private val viewModel: TodoItemsViewModel,
    todoItemsDiffCalculator: TodoItemsDiffCalculator,
    private val navController: NavController
) : ListAdapter<TodoItem, TodoItemViewHolder>(todoItemsDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item_preview, parent, false
        )
        return TodoItemViewHolder(itemView, viewModel, navController)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}