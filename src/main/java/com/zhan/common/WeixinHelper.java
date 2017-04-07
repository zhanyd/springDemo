package com.zhan.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * 该类用于获取微信的相关信息
 * @author zhanyd
 *
 */
public class WeixinHelper {

    private final static String APPID = "wxf3b691a32d460824";
    private final static String APPSECRET = "e841a2a6411a22c6884f820e180e559f";

    public static String getOpenId(String code) {
        try {
        	if(code !=null && code.length()>0)
        	{
        		
        		String content = getResponse("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code");
        		//String content = getResponse("https://open.weimob.com/oauth2/openid/access_token","grant_type=authorization_code&client_id=" + APPID + "&client_secret=" + APPSECRET + "&code=" + code);
        		
        		
        		System.out.println("content = " + content);
        		return content;
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
   
    /**
     * 获取access_token，是公众号的全局唯一票据
     * @return
     */
    public static String getAccessToken() {
        try {
        		String content = getResponse("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET);
        		System.out.println("AccessToken = " + content);
        		return content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 获取用户的基本信息，已判断有木有关注公众号
     * @param accessToken
     * @param openid
     * @return
     */
    public static String getUserInfo(String accessToken,String openid){
    	 try {
     		String content = getResponse("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN");
     		System.out.println("UserInfo = " + content);
     		return content;

     } catch (Exception e) {
         e.printStackTrace();
     }
     return "";
    }
    
    
    
    

    public static String getResponse(String path) throws ProtocolException, MalformedURLException, IOException {
        URL url = new URL(path);
        //得到打开的链接对象  
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //设置请求超时与请求方式  
        conn.setReadTimeout(5 * 1000);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //conn.setDoInput(true);
       
        InputStream inStream = null;
       // OutputStream outStream = null;
        try {
        	
        	/* outStream = conn.getOutputStream();
        	 BufferedWriter write = new BufferedWriter(new OutputStreamWriter(outStream, "utf-8"));
        	 write.write(param);
        	 write.close();*/
        	 
        	 
            inStream = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            String responseContent = tempStr.toString();
            rd.close();
            return responseContent;
        } finally {
            if (inStream != null)
            {
                inStream.close();
            }
            /*if(outStream != null)
            {
            	outStream.close();
            }*/
        }
    }
}
