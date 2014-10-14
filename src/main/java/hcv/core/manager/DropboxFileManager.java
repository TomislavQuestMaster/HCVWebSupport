package hcv.core.manager;

import com.dropbox.core.*;
import hcv.model.FileRequest;
import hcv.trainings.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.util.List;
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
			getClient().getFile("/test/"+training.getId()+".txt", null, new FileOutputStream(file));
		} catch (Exception e) {
			return null;
		}

		return file;
	}

	@Override
	public File fetchFileFromShop(String name) {

		return null;
	}

	@Override
	public void storeFile(Training training, FileItem item, String... optionalPath) throws Exception {

		getClient().uploadFile("/test/" + training.getId() + ".txt", DbxWriteMode.force(), item.getSize(), item.getInputStream());
	}

	@Override
	public void deleteFile(Training training) throws Exception {

		getClient().delete("/test/" + training.getId() + ".txt");
	}

	@Override
	public void storeData(FileRequest request) throws IOException, DbxException {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getData().getBytes());
		getClient().uploadFile("/test/" + request.getName() + ".txt", DbxWriteMode.force(), request.getData().length(), inputStream);
	}

	@Override
	public void zipTrainings(List<Training> trainings, String outputZipFile) {

	}

	private DbxClient getClient() {

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		return new DbxClient(config, ACCESS_TOKEN);
	}
}
