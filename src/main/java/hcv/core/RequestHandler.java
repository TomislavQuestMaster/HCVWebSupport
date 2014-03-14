package hcv.core;

import hcv.database.Database;
import hcv.model.Coach;
import hcv.model.Training;
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

    private final Database database;
    private final ServletFileUpload uploader;
    private final JsonSerializer serializer;

    public RequestHandler() {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = new File("C:\\Users\\Tomo\\Desktop");
        fileFactory.setRepository(filesDir);

        this.uploader = new ServletFileUpload(fileFactory);
        this.serializer = new JsonSerializer();
        this.database = new Database(Utility.getLocalConnection());
    }

    public void onRequest(HttpServletRequest request) throws Exception {

        List<FileItem> fileItemsList = uploader.parseRequest(request);

        if (fileItemsList.size() != 3) {
            throw new Exception("Not enough items: " + fileItemsList.size());
        }

        Coach coach = authenticate(fileItemsList.get(0));
        if (coach == null) {
            return;
        }

        Training training = fetchDetails(fileItemsList.get(1));

        acceptFile(fileItemsList.get(2), coach.getUsername() + File.separator + training.getName());

    }

    private void acceptFile(FileItem fileItem, String path) throws Exception {

        if (!fileItem.getFieldName().equals("file")) {
            throw new Exception("Unsupported state: " + fileItem.getFieldName());
        }

        File file = new File("C:\\Users\\Tomo\\Desktop\\" + path + File.separator + fileItem.getName());
        fileItem.write(file);
    }

    private Coach authenticate(FileItem fileItem) throws Exception {

        if (!fileItem.getFieldName().equals("auth")) {
            throw new Exception("Unsupported state: " + fileItem.getFieldName());
        }

        Coach coach = (Coach) serializer.deserialize(fileItem.getString(), Coach.class);
        if (!database.authenticateCoach(coach)) {
            return null;
        }

        return coach;
    }

    private Training fetchDetails(FileItem fileItem) throws Exception {

        if (!fileItem.getFieldName().equals("details")) {
            throw new Exception("Unsupported state: " + fileItem.getFieldName());
        }

        return (Training) serializer.deserialize(fileItem.getString(), Training.class);
    }

}


//http://www.journaldev.com/1964/servlet-upload-file-and-download-file-example
