package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import service.HelloService;

/**
 *
 * @author Heisaman
 */
public class OAuth2Servlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String code = request.getParameter("code");
        // 调取凭证
        String access_token = HelloService.getAccessToken(HelloService.corpid,HelloService.corpsecret);
        // 打印凭证
        out.print("凭证:"+access_token+"\n ");
        out.print("获取的code:"+code+"\n ");
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+access_token+"&code="+code+"&agentid=0";
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet oauth2Request = new HttpGet(url);
            oauth2Request.addHeader("content-type", "application/x-www-form-urlencoded");
            HttpResponse oauth2Response = httpClient.execute(oauth2Request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(oauth2Response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            out.print("二次验证返回结果：\n"+json);

            JSONObject jsonObj = new JSONObject(json);
            String userId = String.valueOf(jsonObj.get("UserId"));
            String succUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token="+access_token+"&userid="+userId;
            HttpGet succRequest = new HttpGet(succUrl);
            succRequest.addHeader("content-type", "application/x-www-form-urlencoded");
            HttpResponse succResponse = httpClient.execute(succRequest);
            reader = new BufferedReader(new InputStreamReader(succResponse.getEntity().getContent(), "UTF-8"));
            json = reader.readLine();
            jsonObj = new JSONObject(json);
            if(String.valueOf(jsonObj.get("errmsg")).equals("ok")) {
                out.print("验证成功，请返回。");
            } else {
                out.print("验证失败。");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
