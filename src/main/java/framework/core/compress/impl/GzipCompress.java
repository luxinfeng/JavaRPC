package framework.core.compress.impl;

import framework.core.compress.Compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompress implements Compress {

    private static final int BUFFER_SIZE = 1024 * 4;

    public byte[] compress(byte[] bytes) {
        if(bytes == null){
            throw new NullPointerException("bytes arr is null");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try(GZIPOutputStream gzip = new GZIPOutputStream(out)){
            gzip.write(bytes);
            gzip.flush();
            gzip.finish();
            return out.toByteArray();
        } catch (IOException e) {
           throw new RuntimeException("gzip compress error", e);
        }
    }

    public byte[] decompress(byte[] bytes) {
        if(bytes == null){
            throw new NullPointerException("bytes arr is null");
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try(GZIPInputStream ungzip = new GZIPInputStream(new ByteArrayInputStream(bytes))){
            byte[] buffer = new byte[BUFFER_SIZE];
            int n ;
            while((n = ungzip.read(buffer)) > -1){
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("gzip decompress error", e);
        }
    }
}
