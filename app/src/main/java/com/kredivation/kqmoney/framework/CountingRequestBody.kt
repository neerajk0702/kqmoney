package com.kredivation.kqmoney.framework

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

import java.io.IOException

class CountingRequestBody(protected var delegate: RequestBody, protected var listener: Listener) : RequestBody() {

    protected var countingSink: CountingSink?=null;

    override fun contentType(): MediaType? {
        return delegate.contentType()
    }

    override fun contentLength(): Long {
        try {
            return delegate.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return -1
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val bufferedSink: BufferedSink
        countingSink = CountingSink(sink)
        bufferedSink = Okio.buffer(countingSink)
        delegate.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    protected inner class CountingSink(delegate: Sink) : ForwardingSink(delegate) {
        private var bytesWritten: Long = 0

        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)

            bytesWritten += byteCount
            listener.onRequestProgress(bytesWritten, contentLength())
        }

    }

    interface Listener {
        fun onRequestProgress(bytesWritten: Long, contentLength: Long)
    }

}
