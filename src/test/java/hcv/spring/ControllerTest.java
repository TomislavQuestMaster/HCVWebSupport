package hcv.spring;

import hcv.spring.controller.UpdateController;
import hcv.spring.model.Data;
import hcv.spring.model.FetchRequest;
import hcv.spring.service.IDataService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tomo.
 */
public class ControllerTest {

    IDataService service = mock(IDataService.class);

    @Test//TODO refacotr test nicely
    public void test(){

        UpdateController controller = new UpdateController();
        controller.setIDataService(service);

        List<Data> allData = new ArrayList<Data>();
        allData.add(new Data(1,2,"sedmi", 1394L, "IOS"));
        allData.add(new Data(2,3,"drugi", 0L, "IOS"));
        allData.add(new Data(3,4,"treci", 0L, "ANDROID"));
        when(service.getAllData()).thenReturn(allData);

        FetchRequest request = new FetchRequest();
        request.setLastUpdate(1L);
        request.setDeviceName("IOS");
        List<Data> list = controller.fetching(request);
        assertEquals(Integer.valueOf(3),list.get(0).getId());

        request = new FetchRequest();
        request.setLastUpdate(1L);
        request.setDeviceName("ANDROID");
        list = controller.fetching(request);
        assertEquals(Integer.valueOf(2),list.get(0).getId());
    }
}
