package com.sean.blog.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sean.blog.model.AuthToken

@Dao
interface AuthTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace(authToken: AuthToken): Long

    @Query("update auth_token set token = null where account_pk = :pk")
    fun nullifyToken(pk: Int): Int
}