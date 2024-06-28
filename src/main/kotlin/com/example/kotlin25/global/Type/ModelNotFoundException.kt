package com.example.kotlin25.global.Type

data class ModelNotFoundException(
    val modelName: String
) : RuntimeException ("Model $modelName not found")
