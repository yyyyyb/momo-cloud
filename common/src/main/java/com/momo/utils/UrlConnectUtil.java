package com.momo.utils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 通过https链接url获取数据
 */
public class UrlConnectUtil {

    /**
     * url获取数据
     * @param url 地址
     * @return 数据
     */
    public static String getHttpsUrlData(String url) {
        StringBuilder buffer = new StringBuilder();

        try {
            HttpsURLConnection connection = getConnect(url);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            connection.disconnect();

        }catch (Exception e) {
            System.out.println("访问链接出现错误" + url);
            e.printStackTrace();
        }

        return ChineseUtil.convertToSimplifiedChinese(buffer.toString());
    }

    /**
     * 获取一个https连接
     * @param message url地址
     * @return httpsURLConnection
     */
    public static HttpsURLConnection getConnect(String message) {
        HttpsURLConnection connection = null;
        try {
            // 建立SSLContext对象，并使用指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中获得SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(message);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(ssf);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36)");
            connection.setRequestProperty("language", "zh-hans");
            // 设置请求方式（GET/POST）
            connection.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * url获取数据
     * @param url 地址
     * @return 数据
     */
    public static String getHttpUrlData(String url) {
        StringBuilder buffer = new StringBuilder();

        try {
            HttpURLConnection connection = getHttpConnect(url);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            connection.disconnect();
        }catch (Exception e) {
            System.out.println("访问链接出现错误" + url);
        }

        return ChineseUtil.convertToSimplifiedChinese(buffer.toString());
    }

    /**
     * 获取一个http连接
     * @param urlMessage
     * @return
     */
    public static HttpURLConnection getHttpConnect(String urlMessage) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlMessage);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
