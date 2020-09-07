package com.isoldier.basic.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.Scanner;

/**
 * @author jinmeng on 2019/5/31.
 * @version 1.0
 */
public class PipeTest implements Runnable {

    private Pipe pipe;

    public PipeTest(Pipe pipe) {
        this.pipe = pipe;
    }


    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (pipe.source().read(buffer) >= 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                for (int i = 0; buffer.hasRemaining(); i++) {
                    bytes[i] = buffer.get();
                }
                buffer.clear();
                System.out.println("Input : " + new String(bytes));
                if("stop".equals(new String(bytes))){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Pipe pipe = Pipe.open();
        new Thread(new PipeTest(pipe)).start();
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {

                String input = scanner.next();
                pipe.sink().write(ByteBuffer.wrap(input.getBytes()));
                if("stop".equals(input)){
                    break;
                }
            }
        } finally {
            scanner.close();
        }

    }
}