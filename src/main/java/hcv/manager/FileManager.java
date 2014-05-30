package hcv.manager;

import com.dropbox.core.DbxException;
import hcv.AppProperties;
import hcv.model.FileRequest;
import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.*;

/**
 * @author tdubravcevic
 */
public class FileManager implements IFileManager{

	@Override
	public File fetchFile(Training training) {

		return new File("/home/hcv" + File.separator + training.getId() + ".txt");
	}

	@Override
	public void storeFile(Training training, FileItem item) throws Exception {

        if (!item.getFieldName().equals("file")) {
            throw new Exception("Unsupported state: " + item.getFieldName());
        }

        File file = new File("/home/hcv" + File.separator  + item.getName() + ".txt");
        item.write(file);
	}

    @Override
    public void deleteFile(Training training) throws Exception {

        File file = new File("/home/hcv" + File.separator  + training.getId() + ".txt");

        if(!file.delete()){
            throw new Exception("Delete failed");
        }
    }

    @Override
    public void storeData(FileRequest request) throws IOException, DbxException {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getData().getBytes());

		File file = new File("/home/hcv" + File.separator  + request.getName() + ".txt");
		OutputStream outputStream = new FileOutputStream(file);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
    }
}
