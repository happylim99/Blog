package com.sean.blog.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sean.blog.model.AccountProperties

@Dao
interface AccountPropertiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace(accountProperties: AccountProperties): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(accountProperties: AccountProperties)

    @Query("select * from account_properties where pk = :pk")
    fun searchByPk(pk: Int): LiveData<AccountProperties>

    @Query("select * from account_properties where email = :email")
    fun searchByEmail(email: String): AccountProperties?
}