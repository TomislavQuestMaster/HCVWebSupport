package hcv.spring.data.service;

import hcv.spring.data.repositories.UserRepository;
import hcv.spring.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Service
public class UserService{

	@Resource
	private UserRepository userRepository;

	@Transactional
	public User create(User user) {

		return userRepository.save(user);
	}

	@Transactional
	public User delete(Long personId){

		User deleted = userRepository.findOne(personId);

		userRepository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Transactional
	public User update(User updated){

		User user = userRepository.findOne(updated.getId());

		user.setUsername(updated.getUsername());

		return user;
	}


	protected void setPersonRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}