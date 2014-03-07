package servlets;

import database.Database;
import utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author tdubravcevic
 */
public class HCVUploadServlet extends HttpServlet {

    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        Database database = new Database(Utility.getLocalConnection());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                System.out.println(fileItem.getFieldName());
                System.out.println(fileItem.getString());
                if(fileItem.getFieldName().equals("file")){
                File file = new File("C:\\Users\\Tomo\\Desktop\\" + fileItem.getName());
                System.out.println("Absolute Path at server=" + file.getAbsolutePath());
                fileItem.write(file);
                database.insertTraining(fileItem.getFieldName());
                }
            }
        } catch (Exception e) {
            out.println("{\"item\":\"fail: " + e.getMessage() + " \"}");
        }
        out.println("{\"item\":\"SUCCESS\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}