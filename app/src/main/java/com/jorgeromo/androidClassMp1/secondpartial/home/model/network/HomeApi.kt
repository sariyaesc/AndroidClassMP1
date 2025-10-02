package com.jorgeromo.androidClassMp1.secondpartial.home.network

import com.jorgeromo.androidClassMp1.secondpartial.home.model.dto.Event
import retrofit2.http.GET

interface HomeApi {
    @GET("MiguelR222/a9673b9557a1a5bd4542ef860b482913/raw/fe174f333c225b557917ff8589f0200f74308c3f/events.json")
    suspend fun getEvents(): List<Event>
}
