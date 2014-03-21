package hcv.core;

import hcv.AppProperties;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import hcv.serializer.JsonSerializer;
import hcv.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


/**
 * Created by Tomo.
 */
public class RequestHandler {

    private final ServletFileUpload uploader;

    public RequestHandler() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        fileFactory.setRepository(new File(AppProperties.FILE_LOCATION.getValue()));

        this.uploader = new ServletFileUpload(fileFactory);
    }

    public FileItem parseRequest(HttpServletRequest request) throws Exception {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}

        List<FileItem> fileItemsList = uploader.parseRequest(request);

        if (fileItemsList.size() != 2) {
            throw new Exception("Not enough items: " + fileItemsList.size());
        }

		return fileItemsList.get(1);
    }

    private void acceptFile(FileItem fileItem) throws Exception {

        if (!fileItem.getFieldName().equals("file")) {
            throw new Exception("Unsupported state: " + fileItem.getFieldName());
        }

        File file = new File(AppProperties.FILE_LOCATION.getValue() + "\\" + fileItem.getName());
        fileItem.write(file);
    }

}