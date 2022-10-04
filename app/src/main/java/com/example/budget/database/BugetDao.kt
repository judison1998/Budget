package com.example.budget.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BugetDao {
    @Query("SELECT * FROM BudgetItem ORDER BY id DESC")
    fun getAllBudgetItems(): LiveData<List<BudgetItem>>

    @Query("SELECT * FROM BudgetItem WHERE id =:id")
    fun getBudgetItem(id: Int): LiveData<BudgetItem>

    @Insert
    fun insert(budgetItem: BudgetItem)

    @Insert
    fun insert(budgetItems: List<BudgetItem>)


}