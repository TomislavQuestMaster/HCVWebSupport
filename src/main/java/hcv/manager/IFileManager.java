package hcv.manager;

import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

/**
 * @author tdubravcevic
 */
public interface IFileManager {

	File fetchFile(Training training);

	void storeFile(Training training, FileItem item) throws Exception;
}
