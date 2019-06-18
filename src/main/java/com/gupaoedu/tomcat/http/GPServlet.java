package com.gupaoedu.tomcat.http;

/**
 * ClassName:GPServlet
 * Package:com.gupaoedu.tomcat.http
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 11:52
 */
public abstract class GPServlet {
    public  void service(GPRequest request, GpResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }

    protected abstract void doPost(GPRequest request, GpResponse response) throws Exception;

    protected abstract void doGet(GPRequest request, GpResponse response) throws Exception;

}
