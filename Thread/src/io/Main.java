package io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {

//        io1();
//        io2();
//        io3();
//        io4();
//        io5();
//        io6();
//        io7();
//        io8();
//        io9();
//        io10();
    }

    private static void io1() {
        try (OutputStream os = new FileOutputStream("./text.txt")) {

            os.write('a');
            os.write('b');

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io2() {
        try (InputStream is = new FileInputStream("./text.txt")) {
            byte[] bytes = new byte[2];
            is.read(bytes);
            System.out.println(" io  " + new String(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io3() {
        try (InputStream is = new FileInputStream("./text.txt");
             Reader reader = new InputStreamReader(is)) {
            char[] chars = new char[2];
            reader.read(chars);
            System.out.println("io " + new String(chars));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io4() {
        try (InputStream io = new FileInputStream("./text.txt");
             Reader reader = new InputStreamReader(io);
             BufferedReader br = new BufferedReader(reader)) {

            String string = br.readLine();
            System.out.println("io = " + string);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io5() {
        try (OutputStream os = new FileOutputStream("./text.txt");
             BufferedOutputStream bos = new BufferedOutputStream(os)) {

            bos.write('c');
            bos.write('d');

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io6() {
        try (InputStream is = new FileInputStream("./text.txt");
             BufferedInputStream bis = new BufferedInputStream(is);

             OutputStream os = new FileOutputStream("./text2.txt");
             BufferedOutputStream bos = new BufferedOutputStream(os);
        ) {
            byte[] bytes = new byte[1];
            int result;
            while ((result = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io7() {
        try (Socket socket = new Socket("hencoder.com", 80);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.write("GET / HTTP/1.1\n" +
                    "Host:www.example.com\n\n");
            writer.flush();

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io8() {
        try (ServerSocket serverSocket = new ServerSocket(80);
             Socket socket = serverSocket.accept();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.write("HTTP/1.1 200 OK\n" +
                    "Server: nginx/1.14.0 (Ubuntu)\n" +
                    "Date: Tue, 21 Jul 2020 14:56:23 GMT\n" +
                    "Content-Type: text/html\n" +
                    "Content-Length: 612\n" +
                    "Last-Modified: Fri, 11 Oct 2019 04:19:03 GMT\n" +
                    "Connection: keep-alive\n" +
                    "ETag: \"5da002b7-264\"\n" +
                    "Accept-Ranges: bytes\n" +
                    "\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Welcome to nginx!</title>\n" +
                    "<style>\n" +
                    "    body {\n" +
                    "        width: 35em;\n" +
                    "        margin: 0 auto;\n" +
                    "        font-family: Tahoma, Verdana, Arial, sans-serif;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Welcome to nginx!</h1>\n" +
                    "<p>If you see this page, the nginx web server is successfully installed and\n" +
                    "working. Further configuration is required.</p>\n" +
                    "\n" +
                    "<p>For online documentation and support please refer to\n" +
                    "<a href=\"http://nginx.org/\">nginx.org</a>.<br/>\n" +
                    "Commercial support is available at\n" +
                    "<a href=\"http://nginx.com/\">nginx.com</a>.</p>\n" +
                    "\n" +
                    "<p><em>Thank you for using nginx.</em></p>\n" +
                    "</body>\n" +
                    "</html>\n\n");

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io9() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("./text.txt", "r");
             FileChannel channel = randomAccessFile.getChannel();) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);

//            byteBuffer.limit(byteBuffer.position());
//            byteBuffer.position(0);

            byteBuffer.flip();

            System.out.println(Charset.defaultCharset().decode(byteBuffer));
            byteBuffer.clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void io10() {
//        try {
//            ServerSocketChannel channel = ServerSocketChannel.open();
//            channel.bind(new InetSocketAddress(80));
//            SocketChannel socketChannel = channel.accept();
//
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            while (socketChannel.read(byteBuffer) != -1) {
//                byteBuffer.flip();
//                socketChannel.write(byteBuffer);
//                byteBuffer.clear();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(80));
            channel.configureBlocking(false);

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                for (SelectionKey key : selector.keys()) {
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = channel.accept();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.read(byteBuffer) != -1) {
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                            byteBuffer.clear();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
