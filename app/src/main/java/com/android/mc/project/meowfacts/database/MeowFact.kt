package com.android.mc.project.meowfacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

// This is the data class that will hold the database entries.
// The main element is a string, and an Id is also needed
@Entity(tableName = "meow_facts_table")
data class MeowFact(
    @PrimaryKey(autoGenerate = true)
    val factId: Long = 0L,
    @ColumnInfo(name = "fact")
    val fact: String = ""
)