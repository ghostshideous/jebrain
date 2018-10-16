package com.example.niotest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestChannelRead {

    public static void main(String[] args) {
        FileInputStream in = null;
        try {
            in = new FileInputStream("F:\\fd.txt");
            FileChannel channel = in.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            System.out.println(buf.limit() + "==" + buf.position() + "==" + buf.capacity());
            channel.read(buf);
            System.out.println(buf.limit() + "==" + buf.position() + "==" + buf.capacity());
            buf.flip();
            System.out.println(buf.limit() + "==" + buf.position() + "==" + buf.capacity());

            byte[] array = buf.array();
            String s = new String(array, "GB2312");

            System.out.println(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void testOutPut() throws IOException {
        String s = "发的卡放大到放大镜";
        byte[] bytes = s.getBytes();
        FileOutputStream out = new FileOutputStream("F:\\fd.txt");
        FileChannel channel = out.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(50);
        for (int i = 0; i < bytes.length; i++) {
            buf.put(bytes[i]);
        }
        buf.flip();
        channel.write(buf);
        channel.close();
        out.close();
    }


}
