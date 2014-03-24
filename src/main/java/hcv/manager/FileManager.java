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

		return new File(AppProperties.FILE_LOCATION.getValue() + "\\" + training.getId() + ".xml");
	}

	@Override
	public void storeFile(Training training, FileItem item) throws Exception {

        if (!item.getFieldName().equals("file")) {
            throw new Exception("Unsupported state: " + item.getFieldName());
        }

        File file = new File(AppProperties.FILE_LOCATION.getValue() + "\\" + item.getName() + ".xml");
        item.write(file);
	}

    @Override
    public void deleteFile(Training training) throws Exception {

        File file = new File(AppProperties.FILE_LOCATION.getValue() + "\\" + training.getId() + ".xml");

        if(!file.delete()){
            throw new Exception("Delete failed");
        }
    }
}
