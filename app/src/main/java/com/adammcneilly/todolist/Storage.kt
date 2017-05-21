package com.adammcneilly.todolist

import android.content.Context
import android.util.Log
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Reads and writes a list of Tasks to a text file.
 *
 * Created by adam.mcneilly on 5/21/17.
 */
object Storage {
    /**
     * Log Tag for this class to easily filter for errors in the logcat.
     */
    private val LOG_TAG = Storage::class.java.simpleName

    /**
     * The name of the file to read from/write to list items.
     */
    private val FILE_NAME = "todo_list.ser"

    /**
     * Writes a list of tasks to a file.
     */
    fun writeData(context: Context, tasks: List<Task>?) {
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            // Open file and write list
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            oos = ObjectOutputStream(fos)
            oos.writeObject(tasks)
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Could not write to file.")
            e.printStackTrace()
        } finally {
            try {
                oos?.close()
                fos?.close()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Could not close the file.")
                e.printStackTrace()
            }

        }
    }

    /**
     * Reads a list of tasks from a file.
     */
    fun readData(context: Context): MutableList<Task>? {
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null

        var tasks: MutableList<Task>? = ArrayList()

        try {
            // Open file and read list
            fis = context.openFileInput(FILE_NAME)
            ois = ObjectInputStream(fis)

            tasks = ois?.readObject() as? MutableList<Task>
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Could not read from file.")
            e.printStackTrace()
        } finally {
            try {
                ois?.close()
                fis?.close()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Could not close the file.")
                e.printStackTrace()
            }

        }

        return tasks
    }
}