package com.gp.tawk.core.room.daos

import androidx.room.*
import com.gp.tawk.core.room.entities.GitUserEntity

@Dao
interface GitUserDao {
    @get:Query("SELECT * FROM Profile")
    val get: MutableList<GitUserEntity>

    @Query("SELECT * FROM Profile WHERE login OR notes LIKE :query")
    fun getUser(query:String): MutableList<GitUserEntity>

    @Query("SELECT * FROM Profile WHERE id= :id")
    fun getUserById(id:Int): GitUserEntity

    @Query("SELECT count(*) FROM Profile")
    fun getCount(): Int


    @Query("DELETE FROM Profile")
    fun delete()

    @Query("DELETE FROM Profile where id = :id")
    fun deleteById(id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: MutableList<GitUserEntity>)

    @Query("UPDATE Profile SET notes = :notes WHERE id = :id")
    fun updateNotes(id:Int,notes:String)

}
