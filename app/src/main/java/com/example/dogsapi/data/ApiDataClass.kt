package com.example.dogsapi.data

import com.squareup.moshi.Json

data class ApiDataClass(
    val bred_for: String,
    @Json(name="breed_group")
    val breed_group: String,
    val height: Height,
    val id: Int,
    val image: Image,
    val life_span: String,
    @Json(name="name")
    val name: String,
    val origin: String,
    val reference_image_id: String,
    val temperament: String,
    val weight: Weight

/*    val accessibility: Double,
    @Json(name = "activity")
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Int,
    @Json(name = "type")
    val type: String*/
)