package com.gupaoedu.tomcat;

import com.gupaoedu.tomcat.http.GPRequest;
import com.gupaoedu.tomcat.http.GPServlet;
import com.gupaoedu.tomcat.http.GpResponse;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ClassName:GPTomcat
 * Package:com.gupaoedu.tomcat
 * description
 * Created by zhangbin on 2019/6/18.
 *
 * @author: zhangbin q243132465@163.com
 * @Version 1.0.0
 * @CreateTime： 2019/6/18 11:39
 */
public class GPTomcat {
    // 定义一个端口
    private int port = 8080;

    private ServerSocket serverSocket;

    private Map<String, GPServlet> servletMapping=new HashMap<>();

    private Properties webxml=new Properties();

    private void init(){
        // 1.加载web.properties文件,并初始化 servletMapping 对象
        try {
            String WEB_INF= this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")){
                    String servletName=key.replaceAll("\\.url$","");
                    String url=webxml.getProperty(key);
                    String className=webxml.getProperty(servletName+  ".className");
                    // 2.单实例,多线程
                    GPServlet obj=(GPServlet)Class.forName(className).newInstance();
                    servletMapping.put(url,obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void start(){
        // 3.加载配置文件,初始化ServletMapping
        init();
        try {
            // this就先不加了
            serverSocket=new ServerSocket(port);
            System.out.println(" Tomcat已启动.:: 端口是:" + port );
            // 4.等待用户请求, 用一个死循环来等待用户请求
            while (true) {
                Socket client = serverSocket.accept();
                process(client);    
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        GPRequest request=new GPRequest(is);
        GpResponse response=new GpResponse(os);
        // 从协议中拿到url,把相应的Servlet 用反射进行实例化
        String url=request.getUrl();
        if (servletMapping.containsKey(url)) {
            // 调用实例化对象service方法,执行具体的逻辑doGet/doPost方法
            servletMapping.get(url).service(request,response);
        }else{
            response.write("404 !-- NOT Found");
        }
        os.flush();
        os.close();
        is.close();
        client.close();
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }

}
