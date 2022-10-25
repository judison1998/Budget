package com.example.budget.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BugetDao {
    @Query("SELECT * FROM BudgetItem")
    fun getAllBudgetItems(): LiveData<List<BudgetItem>>

    @Query("SELECT * FROM BudgetItem WHERE productID =:id")
    fun getBudgetItem(id: Int): LiveData<BudgetItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budgetItem: BudgetItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budgetItems: List<BudgetItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartItem : CartItem)

    @Query("SELECT * FROM Cart ORDER bY  id")
    fun getAllCartItem() : LiveData<List<CartItem>>

    @Query("DELETE FROM Cart WHERE id = :id")
    fun deleteById(id: Int)

}