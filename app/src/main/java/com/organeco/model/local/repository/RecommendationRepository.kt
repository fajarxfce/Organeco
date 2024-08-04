package com.organeco.model.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.organeco.model.local.entity.Recommendation
import com.organeco.model.local.room.RecommendationDao
import com.organeco.model.local.room.RecommendationRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RecommendationRepository(application: Application) {
    private val mRecomendationDao: RecommendationDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = RecommendationRoomDatabase.getDatabase(application)
        mRecomendationDao = db.recommendationDao()
    }

    fun getAllRecommendation(): LiveData<List<Recommendation>> =
        mRecomendationDao.getAllRecommendation()

    fun insert(recommendation: Recommendation) {
        executorService.execute { mRecomendationDao.insert(recommendation) }
    }

    fun delete(recommendation: Recommendation) {
        executorService.execute { mRecomendationDao.delete(recommendation) }
    }
}