package hcv.core.manager;

import com.dropbox.core.DbxException;
import hcv.model.FileRequest;
import hcv.trainings.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author tdubravcevic
 */
public interface IFileManager {

	File fetchFile(Training training);

	File fetchFileFromShop(String name);

	void storeFile(Training training, FileItem item, String type, String... optionalPath) throws Exception;

    void deleteFile(Training training) throws Exception;

    void storeData(FileRequest data) throws IOException, DbxException;

	void zipTrainings(List<Training> trainings, String outputZipFile);
}
