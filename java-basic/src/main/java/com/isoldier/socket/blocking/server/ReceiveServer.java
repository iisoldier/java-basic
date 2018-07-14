package com.isoldier.socket.blocking.server;

/**
 * @author jinmeng on 2017/11/12.
 * @version 1.0
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * 阻塞服务器 接收数据并返回的过程是一直阻塞的
 * @author jinmeng on 14/07/2018.
 * @version 1.0
 */
public class ReceiveServer {

    public static void main(String[] args) throws IOException {

        ReceiveServer server = new ReceiveServer();

        server.readFromSocketChannel();
    }

    private void readFromSocketChannel() throws IOException {


        /**
         * try-with-resources 是 JDK 7 中一个新的异常处理机制，它能够很容易地关闭在 try-catch 语句块中使用的资源。
         * 所谓的资源（resource）是指在程序完成后，必须关闭的对象。try-with-resources 语句确保了每个资源在语句结束时关闭。
         * 所有实现了 java.lang.AutoCloseable 接口（其中，它包括实现了 java.io.Closeable 的所有对象），可以使用作为资源。
         */
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.socket().bind(new InetSocketAddress(9000));
            SocketChannel socketChannel = serverSocket.accept();

            System.out.println("Connection Established . .?= " + socketChannel.getRemoteAddress());

            String filePath = "/Users/jinmeng/Documents/GitHub/java-basic/java-basic/src/main/resources/socket/recv.txt";
            Path path = Paths.get(filePath);

            try (FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE))) {
                // Allocate a ByteBuffer
                System.out.println("begin reading...");
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (socketChannel.read(buffer) > 0) {
                    buffer.flip();
                    fileChannel.write(buffer);
                    buffer.clear();
                }
            }
            System.out.println("Received File Successfully!");

            //return to client  blocking...
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ByteBuffer returnBuffer = ByteBuffer.allocate(1024);
            returnBuffer.put("Echo Received File Successfully!".getBytes());

            returnBuffer.flip();
            while (returnBuffer.hasRemaining()) {
                socketChannel.write(returnBuffer);
            }

            System.out.println("socketChannel close...!");
        }
    }
}
