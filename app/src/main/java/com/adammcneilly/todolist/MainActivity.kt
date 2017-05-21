package com.adammcneilly.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    var adapter: TaskAdapter? = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as? Toolbar
        setSupportActionBar(toolbar)

        val recyclerView = findViewById(R.id.task_list) as? RecyclerView
        val layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter()
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        val fab = findViewById(R.id.fab) as? FloatingActionButton
        fab?.setOnClickListener { _ ->
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val task = Task(data?.getStringExtra(DESCRIPTION_TEXT).orEmpty())
            adapter?.addTask(task)
        }
    }

    override fun onResume() {
        super.onResume()

        val tasks = Storage.readData(this)

        // We only want to set the tasks if the list is already empty.
        if (tasks != null && (adapter?.tasks?.isEmpty() ?: true)) {
            adapter?.tasks = tasks
        }
    }

    override fun onPause() {
        super.onPause()

        Storage.writeData(this, adapter?.tasks)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getSampleTasks(): MutableList<Task> {
        val task1 = Task("task1")
        val task2 = Task("task2", true)

        return mutableListOf(task1, task2)
    }

    companion object {
        private val ADD_TASK_REQUEST = 0
        val DESCRIPTION_TEXT = "description"
    }
}
