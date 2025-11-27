package com.javalenciab90.detail

import com.javalenciab90.platform.base.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule : TestWatcher() {

    private val dispatcher = UnconfinedTestDispatcher()

    val coroutineContextProvider = object : CoroutineContextProvider {
        override val mainContext: CoroutineContext
            get() = dispatcher

        override val backgroundContext: CoroutineContext
            get() = dispatcher

        override val default: CoroutineContext
            get() = dispatcher
    }

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
        super.finished(description)
    }
}