package com.jiangxk.hencoderlearningview.okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * @author jiangxk
 * @description com.jiangxk.hencoderlearningview.okio
 * @time 2020/7/21  11:37 PM
 */
class Main {
    public static void main(String[] args) {
//        okio();
//        okio2();
        okio3();
    }

    private static void okio() {

        try (Source source = Okio.source(new File("./app/text.txt"))) {

            Buffer buffer = new Buffer();
            source.read(buffer, 1024);

            System.out.println(buffer.readUtf8Line());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void okio2() {
        try (BufferedSource bufferedSource = Okio.buffer(Okio.source(new File("./app/text.txt")))) {

            System.out.println(bufferedSource.readUtf8Line());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void okio3() {
        try (BufferedSource bufferedSource = Okio.buffer(Okio.source(new File("./app/text.txt")));
             BufferedSink bufferedSink = Okio.buffer(Okio.sink(new File("./app/text2.txt")))) {

            int SIZE = 16;
            ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
            while ((bufferedSource.read(byteBuffer) != -1)) {
                byteBuffer.flip();
                bufferedSink.write(byteBuffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
