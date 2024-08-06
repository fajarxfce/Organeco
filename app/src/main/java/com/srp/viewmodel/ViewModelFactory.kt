package com.srp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srp.di.Injection
import com.srp.model.remote.respository.ApiRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val repository: ApiRepository,
    private val repositoryMl: ApiRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UserPreferencesVM::class.java) -> {
                UserPreferencesVM(repository) as T
            }
            modelClass.isAssignableFrom(CalculatorViewModel::class.java) -> {
                CalculatorViewModel(repositoryMl) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context),
                    Injection.provideRepositoryMl(context)
                )
            }.also { instance = it }
    }
}

class RecommendationViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)){
            return RecommendationViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: RecommendationViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): RecommendationViewModelFactory {
            if (INSTANCE == null) {
                synchronized(RecommendationViewModelFactory::class.java) {
                    INSTANCE = RecommendationViewModelFactory(application)
                }
            }
            return INSTANCE as RecommendationViewModelFactory
        }
    }
}