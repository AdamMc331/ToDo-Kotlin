package com.adammcneilly.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val description = findViewById(R.id.task_description) as EditText
        val submit = findViewById(R.id.submit) as Button

        submit.setOnClickListener {
            if (description.text?.toString().isNullOrBlank()) {
                description.error = "Please enter a description"
            } else {
                val data = Intent()
                data.putExtra(MainActivity.DESCRIPTION_TEXT, description.text.toString())
                setResult(Activity.RESULT_OK, data)

                finish()
            }
        }
    }
}
