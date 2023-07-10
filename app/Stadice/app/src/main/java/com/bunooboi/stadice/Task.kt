package com.bunooboi.stadice

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task(@PrimaryKey val id: Int, val name: String, var finished: Boolean, val date: Date)