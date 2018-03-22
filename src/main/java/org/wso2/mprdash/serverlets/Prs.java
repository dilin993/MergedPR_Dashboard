package org.wso2.mprdash.serverlets;

import org.apache.log4j.Logger;
import org.wso2.mprdash.msf4jhttp.HttpHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "getprs",
        urlPatterns = "/prs"
)
public class Prs extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Products.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpHandler httpHandler = new HttpHandler();
            String strProduct = request.getParameter("product").replaceAll(" ","%20");
            String strVersion = request.getParameter("version").replaceAll(" ","%20");
            String strStartDate = request.getParameter("start").replaceAll(" ","%20");
            String strEndDate = request.getParameter("end").replaceAll(" ","%20");
            logger.info("Requesting backend /prs ...");
            String url = "/prs?product=" + strProduct;
            url += "&version=" + strVersion;
            url += "&start=" + strStartDate;
            url += "&end=" + strEndDate;
            String backResponse = httpHandler.get(url);

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

            ServletOutputStream out = response.getOutputStream();
            out.print(backResponse);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
