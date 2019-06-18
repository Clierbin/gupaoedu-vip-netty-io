package com.gupaoedu.demo;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ClassName:BefferDemo
 * Package:com.gupaoedu.demo
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 10:39
 */
public class BefferDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D://咕泡vip/Tom.txt");
        // 创建文件的操作通道
        FileChannel fc = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[10]);
        output("初始化",buffer);
        // 先读一下
        fc.read(buffer);
        output("调用read()",buffer);

        // 准备操作之前,先锁定操作范围
        buffer.flip();
        output("调用flip()",buffer);

        while (buffer.remaining()>0){
            byte b = buffer.get();
            System.out.print((char) b);
        }
        System.out.println("");
        output("调用get()",buffer);

        buffer.clear();

        output("调用clear()",buffer);
        // 把通道关闭
        fc.close();
    }

    private static void output(String step, ByteBuffer buffer) {
        System.out.println(step + "::");

        // 容量数组大小

        System.out.print("capacity" + buffer.capacity() + "," );

        // 当前操作所在的位置,也可以叫做游标

        System.out.print("position" + buffer.position() + ",");

        // 锁定值,flip ,数据操作范围索引,只能在position--limit之间
        System.out.println("limit" + buffer.limit() + ",");

        System.out.println("");
    }
}
