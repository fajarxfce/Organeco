package com.organeco

import com.organeco.model.remote.factory.ApiServiceFactory
import org.junit.Test

class FactoryTest {

    @Test
    fun testFactory() {
        val defaultApiService = ApiServiceFactory.createApiService(ApiServiceFactory.ApiType.DEFAULT)
//        assert(defaultApiService != null)
        println(defaultApiService.toString())

    }

}