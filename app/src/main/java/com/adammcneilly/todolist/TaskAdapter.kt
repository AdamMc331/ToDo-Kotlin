package com.adammcneilly.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

/**
 * Adapter that displays a list of Tasks.
 *
 * Created by adam.mcneilly on 5/20/17.
 */
class TaskAdapter(tasks: MutableList<Task> = ArrayList()) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks: MutableList<Task> = tasks
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context)?.inflate(R.layout.list_item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
        holder?.bindTask(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val descriptionTextView = view?.findViewById(R.id.task_description) as TextView
        val completedCheckBox = view?.findViewById(R.id.task_completed) as CheckBox

        fun bindTask(task: Task) {
            descriptionTextView.text = task.description
            completedCheckBox.isChecked = task.completed

            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tasks[adapterPosition].completed = isChecked
            }
        }
    }
}