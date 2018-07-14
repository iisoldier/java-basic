package com.isoldier.socket.blocking.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 客户端
 * @author jinmeng on 2017/11/12.
 * @version 1.0
 */
public class SenderClient {

    public static void main(String[] args) throws IOException {
        SenderClient client = new SenderClient();
        client.sendFile();
    }

    private void sendFile() throws IOException {


        try(SocketChannel socketChannel = SocketChannel.open()){
            SocketAddress socketAddress = new InetSocketAddress("localhost", 9000);
            socketChannel.connect(socketAddress);
            // file location
            String filePath ="/Users/jinmeng/Documents/GitHub/java-basic/java-basic/src/main/resources/socket/send.txt";
            // Read a file from disk. the size is 1KB
            Path path = Paths.get(filePath);

            try(FileChannel inChannel = FileChannel.open(path)){
                // Allocate a ByteBuffer
                System.out.println("sending content to server");
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while(inChannel.read(buffer) > 0) {
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();
                }
                socketChannel.shutdownOutput();
            }
            // receive data from server
            ByteBuffer serverBuffer = ByteBuffer.allocate(1024);
            while(socketChannel.read(serverBuffer) > 0) {
                System.out.println("receive content from server...");
                serverBuffer.flip();
                System.out.println(StandardCharsets.UTF_8.decode(serverBuffer).toString());
                serverBuffer.clear();
            }
        }

    }

}