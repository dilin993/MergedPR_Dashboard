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
        name = "getproducts",
        urlPatterns = "/products"
)
public class Products extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Products.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpHandler httpHandler = new HttpHandler();
            logger.info("Requesting backend /products ...");
            String backResponse = httpHandler.get("/products");
            logger.info("Got: " + backResponse);
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("credentials", "same origin");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.print(backResponse);

        } catch (IOException e) {
            logger.error("The response output stream failed");
        }
    }
}
