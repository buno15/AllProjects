package com.bunooboi.composesample

import io.mockk.*
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


interface SampleRepository {
    suspend fun fetchData(): String
}

class ExampleUnitTest {
    private lateinit var mockRepository: SampleRepository

    @Before
    fun setUp() {
        mockRepository = mockk()
    }

    @Test
    fun testFetchData() = runBlocking {
        coEvery { mockRepository.fetchData() } returns "Mocked Data"
        val result = mockRepository.fetchData()
        assertEquals("Mocked Data", result)
        coVerify { mockRepository.fetchData() }
    }
}