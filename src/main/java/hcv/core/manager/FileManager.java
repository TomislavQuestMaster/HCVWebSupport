package hcv.core.manager;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcv.model.FileRequest;
import hcv.trainings.model.Training;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author tdubravcevic
 */
public class FileManager implements IFileManager {

	private String basePath;

	public FileManager(String basePath) {

		this.basePath = basePath;
	}

	@Override
	public File fetchFileFromShop(String name) {

		return new File(basePath
				                //+ File.separator + "shop"
				                + File.separator + name);
	}

	@Override
	public File fetchFile(Training training) {

		return new File(basePath + File.separator + training.getId() + ".txt");
	}

	@Override
	public void storeFile(Training training, FileItem item, String type, String... optionalPath) throws Exception {

		//if (!item.getFieldName().equals("file")) {
		//	throw new Exception("Unsupported state: " + item.getFieldName());
		//}

		String extraPath = "";
		for (String path : optionalPath) {
			extraPath += path + File.separator;
		}

		File file = new File(basePath + File.separator + extraPath + training.getId() + "." + type);
		item.write(file);
	}

	@Override
	public void deleteFile(Training training) throws Exception {

		File file = new File(basePath + File.separator + training.getId() + ".txt");

		if (!file.delete()) {
			throw new Exception("Delete failed");
		}
	}

	@Override
	public void storeData(FileRequest request) throws IOException, DbxException {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getData().getBytes());

		File file = new File(basePath + File.separator + request.getName() + ".txt");
		file.createNewFile();
		OutputStream outputStream = new FileOutputStream(file);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
	}

	@Override
	public void zipTrainings(List<Training> trainings, String outputFileName) {

		try {
			FileOutputStream fos = new FileOutputStream(basePath + File.separator + outputFileName + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			ObjectMapper mapper = new ObjectMapper();
			File detailsFile = new File(basePath + File.separator + "shop" + File.separator + outputFileName + "_details.json");
			mapper.writeValue(detailsFile, trainings);

			zipFile(zos, outputFileName + "_details.json");
			for (Training training : trainings) {
				zipFile(zos, training.getId() + ".txt");
				zipFile(zos, training.getId() + ".png");
			}

			zos.closeEntry();
			zos.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void zipFile(ZipOutputStream zos, String name) {

		byte[] buffer = new byte[1024];

		try {
			ZipEntry ze = new ZipEntry(name);
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(basePath + File.separator + "shop" + File.separator + name);
			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			in.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
