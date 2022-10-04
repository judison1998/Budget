package com.example.budget.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
 data class BudgetItem (
 @PrimaryKey(autoGenerate = true) var id : Int = 0,
 var image : Int,
 var itemName : String,
 var price : Int) : Serializable



