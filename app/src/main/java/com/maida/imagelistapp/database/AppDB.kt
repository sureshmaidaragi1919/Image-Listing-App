package com.maida.imagelistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ImageEntity::class), version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun ImagePathDao(): ImagePathDao

    companion object {

        @Volatile
        private var INSTANCE: AppDB? = null

        //Returns the singleton instance for database instance
        fun getDatabase(context: Context, scope: CoroutineScope): AppDB {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, AppDB::class.java, "appDB")
                        .addCallback(AppDbCallBack(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }

    }

    class AppDbCallBack(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.ImagePathDao())
                }
            }
        }

        suspend fun populateDatabase(imagePathDao: ImagePathDao) {
            // Delete all content here.
            imagePathDao.deleteAll()
        }
    }
}