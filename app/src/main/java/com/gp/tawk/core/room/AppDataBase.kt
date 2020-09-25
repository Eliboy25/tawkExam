package com.gp.tawk.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gp.tawk.core.room.daos.*
import com.gp.tawk.core.room.entities.*

@Database(
    entities = [
        GitUserEntity::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao
}