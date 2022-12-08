package com.example.budget.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [BudgetItem::class , CartItem::class],version = 7)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun budgetDao() : BudgetDao

    companion object {
        @Volatile
        private var INSTANCE : BudgetDatabase? = null

        fun getInstance(context: Context) : BudgetDatabase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context,
                    BudgetDatabase::class.java,"budget_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }
}