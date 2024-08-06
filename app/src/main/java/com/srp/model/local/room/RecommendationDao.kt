package com.srp.model.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.srp.model.local.entity.Recommendation

@Dao
interface RecommendationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recommendation: Recommendation)

    @Delete
    fun delete(recommendation: Recommendation)

    @Query("SELECT * from recommendation")
    fun getAllRecommendation(): LiveData<List<Recommendation>>
}