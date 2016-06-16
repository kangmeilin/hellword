package com.kml.test;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
/**
 * 提交参数演示
 * 该程序连接到一个用于查询手机号码所属地的页面
 * 以便查询号码段1330227所在 的省份以及城市
 * @author Liudong
 */
public class SimpleHttpClient {
    public static void main(String[] args) throws IOException
    {
       HttpClient client = new HttpClient();
       client.getHostConfiguration().setHost("jxjy.dwjtaq.com", 80, "http");
       HttpMethod method = getPostMethod();//使用POST方式 提交数据
       client.executeMethod(method);
       //打印服务器返回的状态
       System.out.println(method.getStatusLine());
        //打印结果页面
       String response =
           new String(method.getResponseBodyAsString().getBytes("8859_1"));
       //打印返回的信息
       System.out.println(response);
       method.releaseConnection();
    }
    /**
     * 使用GET方式 提交数据
     * @return
     */
    private static HttpMethod getGetMethod(){
       return new GetMethod("/view/ContinuingEducationWeb/meLogin.jsp?sfz=1330227");
    }
    /**
     * 使用POST方式 提交数据
     * @return
     */
    private static HttpMethod getPostMethod(){
       PostMethod post = new PostMethod("/view/ContinuingEducationWeb/meLogin.jsp");
       NameValuePair simcard = new NameValuePair("sfz", "1330227");
       post.setRequestBody(new NameValuePair[] { simcard});
       return post;
    }
}
