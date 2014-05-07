package hcv.manager;

import com.dropbox.core.DbxException;
import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;

/**
 * @author tdubravcevic
 */
public interface IFileManager {

	File fetchFile(Training training);

	void storeFile(Training training, FileItem item) throws Exception;

    void deleteFile(Training training) throws Exception;

    void storeData(String data) throws IOException, DbxException;
}
