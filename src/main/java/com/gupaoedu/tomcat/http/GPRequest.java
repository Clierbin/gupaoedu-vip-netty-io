package com.gupaoedu.tomcat.http;

import java.io.InputStream;

/**
 * ClassName:GPRequest
 * Package:com.gupaoedu.tomcat.http
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 14:24
 */
public class GPRequest {
    // 方法名
    private String method;
    // url
    private String url;

    public GPRequest(InputStream is) {
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;

            if ((len = is.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
                /**
                 * GET /secondServlet.do HTTP/1.1
                 * Host: localhost:8080
                 * Connection: keep-alive
                 * Pragma: no-cache
                 * Cache-Control: no-cache
                 * Upgrade-Insecure-Requests: 1
                 * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36
                 */
                // Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
                /**
                 * Accept-Encoding: gzip, deflate, br
                 * Accept-Language: zh-CN,zh;q=0.9
                 */
                String line = content.split("\\n")[0];
                String[] arry = line.split("\\s");

                this.method = arry[0];
                this.url = arry[1].split("\\?")[0];
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
