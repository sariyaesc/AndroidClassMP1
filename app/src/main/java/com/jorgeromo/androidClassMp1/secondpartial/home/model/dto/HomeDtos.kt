package com.jorgeromo.androidClassMp1.secondpartial.home.model.dto

// Representa cada evento individual
data class Event(
    val _id: Oid,
    val name: String,
    val address: String,
    val created_by: String,
    val image_url: String,
    val category: String
)

// Para manejar el campo $oid
data class Oid(
    val `$oid`: String
)
