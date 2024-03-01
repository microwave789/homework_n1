package com.example.homework_n1.ui.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.example.homework_n1.R
import com.example.homework_n1.data.model.Importance
import com.example.homework_n1.data.model.TodoItem
import com.example.homework_n1.ioc.ApplicationComponent
import com.example.homework_n1.ui.stateholders.TodoItemsViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// Fragment, that edits an item
@RequiresApi(Build.VERSION_CODES.M)
class TodoItemFragment : Fragment() {
    private val applicationComponent = ApplicationComponent()
    private val viewModel: TodoItemsViewModel by activityViewModels { applicationComponent.viewModelFactory }

    private val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.US)
    private var calendar = Calendar.getInstance()

    // Get the item that we work with through ViewModel
    private fun getCurrentTodoItem(): TodoItem {
        return viewModel.currentTodoItem.value as TodoItem
    }

    // Navigate to the previous Fragment
    private fun navigateTo(id: Int) {
        val navController = findNavController()
        navController.navigate(id)
    }

    // Setup the Fragment and get the rootView
    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        rootView = view
        return view
    }

    // Update/Setup the Fragment on resume
    override fun onResume() {
        super.onResume()
        setupButtons(rootView)
        setupInteractiveViews(rootView)
    }

    // Setup the Exit Button
    private fun setupExitButton(button: ImageButton) {
        button.setOnClickListener {
            navigateTo(R.id.action_fragitem_to_fraglist)
        }
    }

    // Setup the Delete Button
    private fun setupDeleteButton(button: MaterialButton) {
        button.setOnClickListener {
            viewModel.onTodoItemDeleted(getCurrentTodoItem())
            navigateTo(R.id.action_fragitem_to_fraglist)
        }
    }

    // Setup the Save Button
    private fun setupSaveButton(rootView: View, button: MaterialButton) {
        button.setOnClickListener {
            // Collect all data
            val currentTodoItem = getCurrentTodoItem()
            val isNewTodoItem = (currentTodoItem.id == "NEW-ITEM")

            val doneValue = currentTodoItem.done
            val textValue = rootView.findViewById<EditText>(R.id.editor_text).text.toString()
            val importanceValue = when (rootView.findViewById<RadioGroup>(
                R.id.editor_importance
            ).checkedRadioButtonId) {
                R.id.editor_high -> Importance.HIGH
                R.id.editor_mid -> Importance.MID
                R.id.editor_low -> Importance.LOW
                -1 -> Importance.MID
                else -> throw IllegalArgumentException(
                    "checkedId of the RadioGroup doesn't match any values"
                )
            }
            val deadlineValue = currentTodoItem.deadline
            val createdOnValue = currentTodoItem.createdOn
            val editedOnValue = System.currentTimeMillis()

            // Create and store the edited/new item
            var changedTodoItem = TodoItem(
                done = doneValue,
                text = textValue,
                importance = importanceValue,
                deadline = deadlineValue,
                createdOn = createdOnValue,
                editedOn = editedOnValue
            )
            if (isNewTodoItem) {
                viewModel.onTodoItemAdded(changedTodoItem)
            } else {
                changedTodoItem = changedTodoItem.copy(id = currentTodoItem.id)
                viewModel.onTodoItemChanged(changedTodoItem)
            }
            navigateTo(R.id.action_fragitem_to_fraglist)
        }
    }

    // --- Setup all Buttons (look higher) ---
    private fun setupButtons(rootView: View) {
        setupExitButton(rootView.findViewById(R.id.editor_exit))
        setupDeleteButton(rootView.findViewById(R.id.editor_delete))
        setupSaveButton(rootView, rootView.findViewById(R.id.editor_save))
    }

    // Setup the Text Editor
    private fun setupEditText(view: EditText, textValue: String) {
        view.setText(textValue)
    }

    // Setup the Importance picker
    private fun setupRadioGroup(group: RadioGroup, importance: Importance) {
        group.check(when (importance) {
            Importance.LOW  -> R.id.editor_low
            Importance.MID  -> R.id.editor_mid
            Importance.HIGH -> R.id.editor_high
        })
    }

    // Setup the Deadline picker
    private fun setupDeadline(
        clickable: LinearLayout,
        deadlineView: TextView,
        checkBox: CheckBox,
        deadlineValue: Long?
    ) {
        if (deadlineValue == null) {
            deadlineView.text = getString(R.string.preview_date_placeholder)
            checkBox.isChecked = false
        } else {
            deadlineView.text = dateFormat.format(Date(deadlineValue))
            checkBox.isChecked = true
        }

        checkBox.setOnClickListener {
            val currentTodoItem = getCurrentTodoItem()
            if (currentTodoItem.deadline == null) {
                checkBox.isChecked = false
                calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, newYear, newMonth, newDay ->
                        calendar.set(newYear, newMonth, newDay, 0, 0)
                        viewModel.setCurrentTodoItem(
                            currentTodoItem.copy(deadline = calendar.time.time)
                        )
                        checkBox.isChecked = true
                        deadlineView.text = dateFormat.format(calendar.time)
                    },
                    year, month, day
                )
                datePickerDialog.show()
            } else {
                checkBox.isChecked = false
                deadlineView.text = getString(R.string.preview_date_placeholder)
                viewModel.setCurrentTodoItem(currentTodoItem.copy(deadline = null))
            }
        }

        clickable.setOnClickListener {
            val currentTodoItem = getCurrentTodoItem()
            if (currentTodoItem.deadline != null) {
                calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, newYear, newMonth, newDay ->
                        calendar.set(newYear, newMonth, newDay, 0, 0)
                        viewModel.setCurrentTodoItem(
                            currentTodoItem.copy(deadline = calendar.time.time)
                        )
                        deadlineView.text = dateFormat.format(calendar.time)
                    },
                    year, month, day
                )
                datePickerDialog.show()
            }
        }
    }

    // --- Setup the Interactive parts of the Fragment (all of the above before buttons) ---
    private fun setupInteractiveViews(rootView: View) {
        val currentTodoItem = viewModel.currentTodoItem.value as TodoItem
        setupEditText(rootView.findViewById(R.id.editor_text), currentTodoItem.text)
        setupRadioGroup(rootView.findViewById(R.id.editor_importance), currentTodoItem.importance)
        setupDeadline(
            rootView.findViewById(R.id.editor_date),
            rootView.findViewById(R.id.editor_date_view),
            rootView.findViewById(R.id.editor_has_date),
            currentTodoItem.deadline
        )
    }
}