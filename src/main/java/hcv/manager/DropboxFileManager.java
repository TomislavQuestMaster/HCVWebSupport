package hcv.manager;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import hcv.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;

/**
 * @author tdubravcevic
 */
public class DropboxFileManager implements IFileManager {

	private final String ACCESS_TOKEN = "w_QreAXaNn4AAAAAAAAAASvqd8vcfJYBUt_yDWhpPdYggKrziRuhl8EJYwYXtKag";

	@Override
	public File fetchFile(Training training) {

		File file = new File(training.getId()+".xml");

		try {
			getClient().getFile("/eclipse-formatter.xml", null, new FileOutputStream(file));
		} catch (Exception e) {
			return null;
		}

		return file;
	}

	@Override
	public void storeFile(Training training, FileItem item) throws Exception {

		getClient().uploadFile("/"+training.getId()+".xml", DbxWriteMode.add(), item.getSize(), item.getInputStream());
	}

	@Override
	public void deleteFile(Training training) throws Exception {

		getClient().delete("/"+training.getId()+".xml");
	}

	private DbxClient getClient(){
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		return new DbxClient(config, ACCESS_TOKEN);
	}
}
