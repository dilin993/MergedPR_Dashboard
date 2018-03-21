package org.wso2.mprdash.serverlets;

import org.apache.log4j.Logger;
import org.wso2.mprdash.msf4jhttp.HttpHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.*;

@WebServlet(
        name = "setdoc",
        urlPatterns = "/setdoc"
)
public class SetDoc extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Products.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpHandler httpHandler = new HttpHandler();
            String backResponse = httpHandler.post("/setdoc",getBody(request));

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

            ServletOutputStream out = response.getOutputStream();
            out.print(backResponse);
        } catch (IOException e) {
            logger.error("The response output stream failed");
        }
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}

