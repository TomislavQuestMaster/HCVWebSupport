package hcv.core.manager;

import com.dropbox.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.Locale;

/**
 * @author tdubravcevic
 */
@Controller
public class DropboxController {

	private final String APP_KEY = "gxxik35ef1g6cyy";
	private final String APP_SECRET = "tb7atde7l7fe84z";

	private final String ACCESS_TOKEN = "w_QreAXaNn4AAAAAAAAAASvqd8vcfJYBUt_yDWhpPdYggKrziRuhl8EJYwYXtKag";

	@RequestMapping(value = "/dropbox", method = RequestMethod.GET)
	public void register() {

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig(
				"JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

		String authorizeUrl = webAuth.start();
		System.out.println("1. Go to: " + authorizeUrl);
		System.out.println("2. Click \"Allow\" (you might have to log in first)");
		System.out.println("3. Copy the authorization code.");
		String code = null;
		try {
			code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DbxAuthFinish authFinish = null;
		try {
			authFinish = webAuth.finish(code);
		} catch (DbxException e) {
			e.printStackTrace();
		}
		String accessToken = authFinish.accessToken;

		DbxClient client = new DbxClient(config, accessToken);
		try {
			System.out.println("Linked account: " + client.getAccountInfo().displayName);
			System.out.println(accessToken);
		} catch (DbxException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/drop", method = RequestMethod.GET)
	public void upload() throws IOException, DbxException {

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, ACCESS_TOKEN);

		File inputFile = new File("C:\\Users\\tdubravcevic\\Desktop\\eclipse-formatter.xml");
		FileInputStream inputStream = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = client.uploadFile("/eclipse-formatter.xml", DbxWriteMode.add(), inputFile.length(), inputStream);
			System.out.println("Uploaded: " + uploadedFile.toString());
		} finally {
			inputStream.close();
		}
	}

	@RequestMapping(value = "/undrop", method = RequestMethod.GET)
	public void download() throws IOException, DbxException {

		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxClient client = new DbxClient(config, ACCESS_TOKEN);

		FileOutputStream outputStream = new FileOutputStream("C:\\Users\\tdubravcevic\\Desktop\\eclipse-formatter2.xml");
		try {
			DbxEntry.File downloadedFile = client.getFile("/eclipse-formatter.xml", null,
														  outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}
}
