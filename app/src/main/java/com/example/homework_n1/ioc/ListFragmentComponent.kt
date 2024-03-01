package com.example.homework_n1.ioc

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.homework_n1.ui.fragments.ListFragment
import com.example.homework_n1.ui.stateholders.TodoItemsViewModel
import com.example.homework_n1.ui.view.TodoItemsDiffCalculator
import com.example.homework_n1.ui.view.TodoItemsListAdapter

// Component that contains relevant information for the ListFragment
// Creates an adapter
@RequiresApi(Build.VERSION_CODES.M)
class ListFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: ListFragment,
    val viewModel: TodoItemsViewModel
) {
    val adapter = TodoItemsListAdapter(
        viewModel,
        TodoItemsDiffCalculator(),
        fragment.findNavController()
    )
}
