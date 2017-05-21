package com.adammcneilly.todolist

import java.io.Serializable

/**
 * Represents a task that must be completed.
 *
 * Created by adam.mcneilly on 5/20/17.
 */
data class Task(var description: String, var completed: Boolean = false) : Serializable