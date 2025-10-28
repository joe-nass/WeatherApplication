package com.example.weatherapplication.domain.use_cases

interface UseCase<in P, out R> {
    suspend operator fun invoke(params: P): R
}