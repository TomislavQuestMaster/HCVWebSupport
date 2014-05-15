package hcv.spring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hcv.data.repositories.TrainingRepository;
import hcv.manager.IFileManager;
import hcv.model.FetchRequest;
import hcv.model.Response;
import hcv.model.Training;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author tdubravcevic
 */
@RunWith(MockitoJUnitRunner.class)
public class SynchronizationControllerTest {

	@InjectMocks
	private SynchronizationController controller = new SynchronizationController();

	@Mock
	private TrainingRepository repository;

    @Mock
    private IFileManager manager;

    private Training training;
    private FetchRequest request;
    private Response response;

    @Before
	public void setUp() {

		initMocks(this);
        Training newTraining = new Training();
        newTraining.setId(0L);
        when(repository.save(any(Training.class))).thenReturn(newTraining);
	}

    @Test
    public void ifTrainingIsConflictedOneWithNewNameIsCreated() throws IOException {

        Training training = new Training();
        training.setId(1L);
        training.setUpdatingDeviceName("ANDROID");
        training.setLastUpdate(2L);

        givenTrainingInDatabaseForId(1L, training);
        givenTraining(1,"TEST");
        givenFetchRequest(1,"IOS");

        whenNewUpdate();

        thenInsertedTrainingHasName("TEST_IOS");
    }

    @Test
    public void trainingIsNotConflictedIfNoneInDatabase() throws IOException {

        givenTrainingInDatabaseForId(1L, null);
        givenTraining(1,"TEST");
        givenFetchRequest(1,"IOS");

        whenNewUpdate();

        thenInsertedTrainingHasName("TEST");
    }

    @Test
    public void trainingIsNotConflictedIfFromSameDevice() throws IOException {

        Training training = new Training();
        training.setId(1L);
        training.setUpdatingDeviceName("IOS");
        training.setLastUpdate(2L);

        givenTrainingInDatabaseForId(1L, training);
        givenTraining(1,"TEST");
        givenFetchRequest(1,"IOS");

        whenNewUpdate();

        thenInsertedTrainingHasName("TEST");
    }

    @Test
    public void trainingIsNotConflictedIfUpdatingTimeIsHigher() throws IOException {

        Training training = new Training();
        training.setId(1L);
        training.setUpdatingDeviceName("IOS");
        training.setLastUpdate(1L);

        givenTrainingInDatabaseForId(1L, training);
        givenTraining(1,"TEST");
        givenFetchRequest(3,"IOS");

        whenNewUpdate();

        thenInsertedTrainingHasName("TEST");
    }

    @Test
	public void idInResponseIsSetCorrectly() throws IOException {

        Training training = new Training();
        training.setId(1L);
        training.setUpdatingDeviceName("ios");
        training.setLastUpdate(1L);

        givenTrainingInDatabaseForId(1, training);
        givenTraining(1,"TEST");
        givenFetchRequest(1,"ios");
        givenTrainingWithIdFromSave(2);

        whenNewUpdate();

        thenResponseIdIs(2);
	}

    private void givenTraining(long id, String name) {
        training = new Training();
        training.setId(id);
        training.setName(name);
    }

    private void givenFetchRequest(long lastUpdate, String deviceName){
        request = new FetchRequest();
        request.setDeviceName(deviceName);
        request.setLastUpdate(lastUpdate);
    }

    private void givenTrainingInDatabaseForId(long id, Training training) {

        when(repository.findById(id)).thenReturn(training);
    }

    private void givenTrainingWithIdFromSave(long id) {

        Training newTraining = new Training();
        newTraining.setId(id);
        when(repository.save(any(Training.class))).thenReturn(newTraining);
    }

    private void whenNewUpdate() throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("training", mapper.valueToTree(training));
        ((ObjectNode) rootNode).put("request", mapper.valueToTree(request));
        mapper.writeValue(writer, rootNode);

        response = controller.newUpdate(writer.toString());
    }

    private void thenInsertedTrainingHasName(String name) {
        ArgumentCaptor<Training> captor = ArgumentCaptor.forClass(Training.class);
        verify(repository).save(captor.capture());
        assertEquals(name, captor.getValue().getName());
    }

    private void thenResponseIdIs(Integer id) {
        assertEquals(id, response.getStatus());
        assertEquals("Update successful", response.getMessage());
    }
}
