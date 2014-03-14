package hcv.servlets;

import hcv.core.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author tdubravcevic
 */
public class HCVUploadServlet extends HttpServlet {

    private RequestHandler handler;

    @Override
    public void init() throws ServletException {
        //File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        handler = new RequestHandler();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            handler.onRequest(request);
        } catch (Exception e) {
            out.println("{\"item\":\"fail: " + e.getMessage() + " \"}");
            return;
        }
        out.println("{\"item\":\"SUCCESS\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}