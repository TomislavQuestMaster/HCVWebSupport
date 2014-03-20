package hcv.spring.controller;

import hcv.data.repositories.TrainingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

	@Before
	public void setUp() {

		initMocks(this);
	}

	@Test
	public void test(){


	}
}
