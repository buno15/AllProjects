package com.bunooboi.composesample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AppViewModel

    @Before
    fun setUp() {
        viewModel = AppViewModel()
    }

    @Test
    fun updateMessage_updatesMessageValue() {
        val expectedMessage = "Hello, World"
        viewModel.updateMessage(expectedMessage)
        assert(viewModel.message.value == expectedMessage)
    }

    @Test
    fun testUpdateMessageByMockK() {
        val mock = mockk<AppViewModel>()
        every { mock.updateMessage("test") } returns "test"
        val result = mock.updateMessage("test")
        assertEquals("test", result)
        verify { mock.updateMessage("test") }
    }
}