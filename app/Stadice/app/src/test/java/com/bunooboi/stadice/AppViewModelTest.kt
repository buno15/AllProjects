package com.bunooboi.stadice

import com.bunooboi.stadice.data.RoomRepository
import com.bunooboi.stadice.data.SharedPreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

@ExperimentalCoroutinesApi
class AppViewModelTest {
    private lateinit var viewModel: AppViewModel
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository
    private lateinit var roomRepository: RoomRepository

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)
}