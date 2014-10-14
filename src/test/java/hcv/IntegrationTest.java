package hcv;

import hcv.core.RequestHandler;
import hcv.trainings.controller.SynchronizationController;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

/**
 * @author tdubravcevic
 */
public class IntegrationTest {

	private FileItem fileItem = mock(FileItem.class);
	private HttpServletRequest request = mock(HttpServletRequest.class);
	private RequestHandler handler = mock(RequestHandler.class);

	@Test
	public void test() throws Exception {



		when(handler.parseRequest(request)).thenReturn(Arrays.asList(fileItem));

		SynchronizationController controller = new SynchronizationController();
		controller.upload(request);
	}
}
