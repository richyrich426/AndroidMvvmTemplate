package com.example.dogs.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//(Data access object)
@Dao
interface DogDao {
    // suspend runs on separate thread
    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("SELECT * FROM dogBreed")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogBreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    @Query("DELETE FROM dogBreed")
    suspend fun deleteAllDogs()
}