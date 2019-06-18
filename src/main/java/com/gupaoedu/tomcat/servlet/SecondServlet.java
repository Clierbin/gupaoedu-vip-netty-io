package com.gupaoedu.tomcat.servlet;

import com.gupaoedu.tomcat.http.GPRequest;
import com.gupaoedu.tomcat.http.GPServlet;
import com.gupaoedu.tomcat.http.GpResponse;

/**
 * ClassName:SecondServlet
 * Package:com.gupaoedu.tomcat.servlet
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 15:01
 */
public class SecondServlet extends GPServlet {
    @Override
    public void doGet(GPRequest request, GpResponse response) throws Exception {
        this.doPost(request,response);
    }
    @Override
    public void doPost(GPRequest request, GpResponse response) throws Exception {
        response.write(" This is Second Servlet !!!");
    }
}
