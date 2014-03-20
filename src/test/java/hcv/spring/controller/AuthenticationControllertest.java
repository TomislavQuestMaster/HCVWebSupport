package hcv.spring.controller;

import hcv.data.service.UserService;
import hcv.model.Response;
import hcv.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author tdubravcevic
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

	@InjectMocks
	private AuthenticationController controller = new AuthenticationController();

	@Mock
	private UserService service;

	private Response response;
	private User user;

	@Before
	public void setUp() {

		initMocks(this);
	}

	@Test
	public void userIsRegisterIfHeIsNotFound() {

		User user = new User("coach1", "1234", null, null, null);
		givenUser(user);
		givenFoundUser(null);

		whenRegister();

		thenRegistrationSucceeded();
	}

	@Test
	public void userFailsToRegisteredIfHeIsAlreadyRegistered() {

		User user = new User("coach1", "1234", null, null, null);
		givenUser(user);
		givenFoundUser(user);

		whenRegister();

		thenRegistrationFailed();
	}

	@Test
	public void userIsAuthenticated() {

		User user = new User("coach1", "1234", null, null, null);
		givenUser(user);
		givenFoundUser(user);

		whenAuthenticate();

		thenAuthenticationSucceeded();
	}

	@Test
	public void userFailsToAuthenticateWhenNotRegistered() {

		User user = new User("coach1", "1234", null, null, null);
		givenUser(user);
		givenFoundUser(null);

		whenAuthenticate();

		thenAuthenticationFailed("Verification failed, not found", 2);
	}

	@Test
	public void userFailsToAuthenticateWithWrongPassword() {

		User user = new User("coach1", "1234", null, null, null);
		User foundUser = new User("coach1", "12345", null, null, null);
		givenUser(user);
		givenFoundUser(foundUser);

		whenAuthenticate();

		thenAuthenticationFailed("Verification failed, wrong password", 3);
	}

	private void thenAuthenticationFailed(String message, Integer status) {

		assertEquals(message, response.getMessage());
		assertEquals(status, response.getStatus());
	}

	private void thenRegistrationFailed() {

		assertEquals("User exists", response.getMessage());
		assertEquals(Integer.valueOf(1), response.getStatus());
	}

	private void thenRegistrationSucceeded() {

		verify(service).create(eq(user));
		assertEquals("Created user", response.getMessage());
		assertEquals(Integer.valueOf(0), response.getStatus());
	}

	private void thenAuthenticationSucceeded() {

		assertEquals("Verification succeeded", response.getMessage());
		assertEquals(Integer.valueOf(0), response.getStatus());
	}

	private void whenRegister() {

		response = controller.registering(user);
	}

	private void whenAuthenticate() {

		response = controller.verifying(user);
	}

	public void givenUser(User user) {

		this.user = user;
	}

	private void givenFoundUser(User user) {

		when(service.findByUsername(this.user.getUsername())).thenReturn(user);
	}

}
