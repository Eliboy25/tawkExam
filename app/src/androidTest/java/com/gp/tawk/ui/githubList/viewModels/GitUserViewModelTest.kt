package com.gp.tawk.ui.githubList.viewModels

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.gp.tawk.core.room.AppDataBase
import com.gp.tawk.core.room.daos.GitUserDao
import com.gp.tawk.core.room.entities.GitUserEntity
import io.reactivex.internal.util.NotificationLite.getValue
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class GitUserViewModelTest {

    private var gitDao: GitUserDao? = null

    @Before
    fun setup() {

        gitDao = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
            AppDataBase::class.java).build().gitUserDao()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_Food_Item() {



        val users  =  mutableListOf<GitUserEntity>()

        users.add(GitUserEntity(1, "Banana", "Desc"))
        users.add(GitUserEntity(2, "ELi", "Desc"))
        users.add(GitUserEntity(3, "Rudy", "Desc"))
        gitDao?.insert(users)
        val user = gitDao?.getUserById(1)!!
        assertEquals("Banana", user.login)
    }

    @Test
    fun should_Flush_All_Data(){
        gitDao?.delete()
        assertEquals(gitDao?.getCount(), 0)
    }


}
