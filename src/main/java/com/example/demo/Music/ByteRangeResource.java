package com.example.demo.Music;

import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;


public class ByteRangeResource extends UrlResource {

    private final long start;
    private final long length;

    public ByteRangeResource(URI url, long start, long length) throws IOException {
        super(url);
        this.start = start;
        this.length = length;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = super.getInputStream();
        inputStream.skip(start);
        return new LimitedInputStream(inputStream, length);
    }

    private static class LimitedInputStream extends InputStream {
        private final InputStream delegate;
        private long bytesLeft;

        public LimitedInputStream(InputStream delegate, long limit) {
            this.delegate = delegate;
            this.bytesLeft = limit;
        }

        @Override
        public int read() throws IOException {
            if (bytesLeft <= 0) {
                return -1;
            }
            int result = delegate.read();
            if (result != -1) {
                bytesLeft--;
            }
            return result;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (bytesLeft <= 0) {
                return -1;
            }
            int bytesToRead = (int) Math.min(len, bytesLeft);
            int result = delegate.read(b, off, bytesToRead);
            if (result != -1) {
                bytesLeft -= result;
            }
            return result;
        }

        @Override
        public void close() throws IOException {
            delegate.close();
        }
    }
}
