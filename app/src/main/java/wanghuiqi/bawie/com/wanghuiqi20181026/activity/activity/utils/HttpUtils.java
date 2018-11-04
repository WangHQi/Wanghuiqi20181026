package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    private static String result;

    public static String getFromString(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //get请求
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);

            //为200成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                result = getFromInputStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
            boas.write(buffer, 0, len);
            boas.flush();
        }
        is.close();
        result = boas.toString();
        boas.close();

        return result;
    }

}
