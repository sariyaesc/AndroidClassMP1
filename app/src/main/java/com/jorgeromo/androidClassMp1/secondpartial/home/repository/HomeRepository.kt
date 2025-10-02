package com.jorgeromo.androidClassMp1.secondpartial.home.repository

import com.jorgeromo.androidClassMp1.secondpartial.home.model.dto.Event
import com.jorgeromo.androidClassMp1.secondpartial.home.network.RetrofitInstance

class HomeRepository {

    private val api = RetrofitInstance.api

    suspend fun getEvents(): Result<List<Event>> {
        return try {
            val events = api.getEvents()
            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
