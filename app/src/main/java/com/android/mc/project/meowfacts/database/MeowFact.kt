package com.android.mc.project.meowfacts.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "meow_facts_table")
data class MeowFact(
    @PrimaryKey
    val fact: String = ""
)