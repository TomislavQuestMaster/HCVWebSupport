package hcv.core;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import hcv.serializer.JsonSerializer;
import hcv.utils.Utility;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


/**
 * Created by Tomo.
 */
public class RequestHandler {

    private final ServletFileUpload uploader;
    private final JsonSerializer serializer;

    public RequestHandler() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = new File("C:\\Users\\Tomo\\Desktop");
        fileFactory.setRepository(filesDir);

        this.uploader = new ServletFileUpload(fileFactory);
        this.serializer = new JsonSerializer();
    }

    public void onRequest(HttpServletRequest request) throws Exception {

        List<FileItem> fileItemsList = uploader.parseRequest(request);

        if (fileItemsList.size() != 2) {
            throw new Exception("Not enough items: " + fileItemsList.size());
        }

        acceptFile(fileItemsList.get(1));

    }

    private void acceptFile(FileItem fileItem) throws Exception {

        if (!fileItem.getFieldName().equals("file")) {
            throw new Exception("Unsupported state: " + fileItem.getFieldName());
        }

        File file = new File("C:\\Users\\tdubravcevic\\Downloads\\HCV\\" + fileItem.getName());
        fileItem.write(file);
		//TODO update state in database
		//database.insertTraining();
		//database.updateTraining();
    }

}


//http://www.journaldev.com/1964/servlet-upload-file-and-download-file-example
