package hcv.manager;

import com.dropbox.core.*;
import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.util.Locale;

/**
 * @author tdubravcevic
 */
public class DropboxFileManager implements IFileManager {

	private final String ACCESS_TOKEN = "w_QreAXaNn4AAAAAAAAAASvqd8vcfJYBUt_yDWhpPdYggKrziRuhl8EJYwYXtKag";

	@Override
	public File fetchFile(Training training) {

		File file = new File(training.getId() + ".txt");

		try {
			getClient().getFile("/"+training.getId()+".txt", null, new FileOutputStream(file));
		} catch (Exception e) {
			return null;
		}

		return file;
	}

	@Override
	public void storeFile(Training training, FileItem item) throws Exception {

		getClient().uploadFile("/" + training.getId() + ".txt", DbxWriteMode.force(), item.getSize(), item.getInputStream());
	}

	@Override
	public void deleteFile(Training training) throws Exception {

		getClient().delete("/" + training.getId() + ".txt");
	}

    @Override
    public void storeData(String data) throws IOException, DbxException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        getClient().uploadFile("/" + "TEST" + ".txt", DbxWriteMode.force(), data.length(), inputStream);
    }

	private DbxClient getClient() {

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		return new DbxClient(config, ACCESS_TOKEN);
	}
}
