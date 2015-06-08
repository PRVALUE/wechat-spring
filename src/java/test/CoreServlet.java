package test;

import encryption.WXBizMsgCrypt;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.CoreService;
/**
 *
 * @author Heisaman
 */
public class CoreServlet extends HttpServlet{
    public static final String sToken = "UOCJDzA2bku9TEKUzLnBMGVM67";
    public static final String sCorpID = "wxe577618fe713e475";
    public static final String sEncodingAESKey = "BKa7REbgMlGiEjSAW564cLuAwvsrPoq2ycQwnxD1p2A";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {
        String result = null;
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            String sVerifyMsgSig = request.getParameter("msg_signature");
            // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
            String sVerifyTimeStamp = request.getParameter("timestamp");
            // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
            String sVerifyNonce = request.getParameter("nonce");
            // String sVerifyEchoStr = HttpUtils.ParseUrl("echostr");
            String sVerifyEchoStr = request.getParameter("echostr");

            result = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
                            sVerifyNonce, sVerifyEchoStr);
            out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用核心业务类接收消息、处理消息
        String respMessage = CoreService.processRequest(request);
        System.out.println("respMessage=" + respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
