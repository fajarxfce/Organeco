package com.srp.model.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srp.model.local.entity.Recommendation

@Database(entities = [Recommendation::class], version = 1)
abstract class RecommendationRoomDatabase : RoomDatabase() {
    abstract fun recommendationDao(): RecommendationDao

    companion object {
        @Volatile
        private var INSTANCE: RecommendationRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): RecommendationRoomDatabase {
            if (INSTANCE == null) {
                synchronized(RecommendationRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RecommendationRoomDatabase::class.java, "recommendation.db"
                    )
                        .build()
                }
            }
            return INSTANCE as RecommendationRoomDatabase
        }
    }
}