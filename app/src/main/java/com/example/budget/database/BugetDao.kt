package com.example.budget.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BugetDao {
    @Query("SELECT * FROM BudgetItem ORDER BY id DESC")
    fun getAllBudgetItems():LiveData<List<BudgetItem>>

    @Insert
    fun insert(budgetItem: BudgetItem)

}