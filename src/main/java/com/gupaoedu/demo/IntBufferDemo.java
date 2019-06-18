package com.gupaoedu.demo;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * ClassName:IntBufferDemo
 * Package:com.gupaoedu.demo
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 10:30
 */
public class IntBufferDemo {
    public static void main(String[] args) {
        // 分配新的 int缓冲区,参数为缓存区容量
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); i++) {
           int j=2*(i+1);
            buffer.put(j);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            int i = buffer.get();
            System.out.print(i + " ");
        }
    }
}
