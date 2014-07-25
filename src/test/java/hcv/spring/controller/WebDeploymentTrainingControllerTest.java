package hcv.spring.controller;

import hcv.model.DatabaseFilter;
import hcv.model.SortType;
import hcv.trainings.controller.WebDeploymentTrainingController;
import hcv.trainings.model.Training;
import hcv.trainings.model.TrainingLevel;
import hcv.core.config.SystemTestConfiguration;
import hcv.utils.TestProfile;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author tdubravcevic
 */
@Ignore
@ContextConfiguration(classes = SystemTestConfiguration.class)
@ActiveProfiles(TestProfile.value)
@RunWith(SpringJUnit4ClassRunner.class)
public class WebDeploymentTrainingControllerTest {

	@Inject
	private WebDeploymentTrainingController controller = new WebDeploymentTrainingController();

    @Test
	public void test() throws IOException {
		//controller.insert(new Training());
		assertEquals(Long.valueOf(0), controller.count());
	}

	@Test
	public void test1() throws IOException {

		DatabaseFilter filter = new DatabaseFilter();
		filter.setSortBy(SortType.FUN);
		filter.addLevel(TrainingLevel.PROF);

		Training training = new Training();
		training.setFun((short) 3);
		training.addLevel(TrainingLevel.PROF);
		controller.insert(training);

		training = new Training();
		training.setFun((short) 2);
		training.addLevel(TrainingLevel.PROF);
		controller.insert(training);

		List<Training> result = controller.filter(filter);

		System.out.println(result.size());
	}


}
