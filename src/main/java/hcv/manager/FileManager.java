package hcv.manager;

import hcv.AppProperties;
import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.File;

/**
 * @author tdubravcevic
 */
public class FileManager implements IFileManager{

	@Override
	public File fetchFile(Training training) {

		return null;
	}

	@Override
	public void storeFile(Training training, FileItem item) {

	}
}
