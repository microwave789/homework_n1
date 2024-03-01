package com.example.homework_n1.ioc

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.example.homework_n1.ui.view.TodoItemsPreviewController

// Component that contains information for the RecyclerView itself
@RequiresApi(Build.VERSION_CODES.M)
class TodoItemsPreviewComponent(
    fragmentComponent: ListFragmentComponent,
    root: View,
    lifecycleOwner: LifecycleOwner
) {
    val todoItemsPreviewController = TodoItemsPreviewController(
        fragmentComponent.fragment.requireActivity(),
        root,
        fragmentComponent.adapter,
        lifecycleOwner,
        fragmentComponent.viewModel
    )
}
