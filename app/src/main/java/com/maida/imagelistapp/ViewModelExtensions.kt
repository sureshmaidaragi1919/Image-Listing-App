package com.maida.imagelistapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

class CloseableCoroutineScope(
    override val coroutineContext: CoroutineContext
) : Closeable, CoroutineScope {
    override fun close() {
        coroutineContext.cancel()
    }
}