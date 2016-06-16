package com.kml.test;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
/**
 * �ύ������ʾ
 * �ó������ӵ�һ�����ڲ�ѯ�ֻ����������ص�ҳ��
 * �Ա��ѯ�����1330227���� ��ʡ���Լ�����
 * @author Liudong
 */
public class SimpleHttpClient {
    public static void main(String[] args) throws IOException
    {
       HttpClient client = new HttpClient();
       client.getHostConfiguration().setHost("jxjy.dwjtaq.com", 80, "http");
       HttpMethod method = getPostMethod();//ʹ��POST��ʽ �ύ����
       client.executeMethod(method);
       //��ӡ���������ص�״̬
       System.out.println(method.getStatusLine());
        //��ӡ���ҳ��
       String response =
           new String(method.getResponseBodyAsString().getBytes("8859_1"));
       //��ӡ���ص���Ϣ
       System.out.println(response);
       method.releaseConnection();
    }
    /**
     * ʹ��GET��ʽ �ύ����
     * @return
     */
    private static HttpMethod getGetMethod(){
       return new GetMethod("/view/ContinuingEducationWeb/meLogin.jsp?sfz=1330227");
    }
    /**
     * ʹ��POST��ʽ �ύ����
     * @return
     */
    private static HttpMethod getPostMethod(){
       PostMethod post = new PostMethod("/view/ContinuingEducationWeb/meLogin.jsp");
       NameValuePair simcard = new NameValuePair("sfz", "1330227");
       post.setRequestBody(new NameValuePair[] { simcard});
       return post;
    }
}
