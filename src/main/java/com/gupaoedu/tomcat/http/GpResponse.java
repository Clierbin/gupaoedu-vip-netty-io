package com.gupaoedu.tomcat.http;

import java.io.OutputStream;

/**
 * ClassName:GpResponse
 * Package:com.gupaoedu.tomcat.http
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 14:25
 */
public class GpResponse {
    private OutputStream os;

    public GpResponse(OutputStream os) {
        this.os=os;
    }

    public void write(String s) throws Exception {
        StringBuilder sb=new StringBuilder();
        sb.append("HTTP/1.1 200 ok\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        os.write(sb.toString().getBytes());
    }
}
