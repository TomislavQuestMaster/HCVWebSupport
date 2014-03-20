package hcv.data.service;

import hcv.data.repositories.UserRepository;
import hcv.model.user.User;
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
	private UserRepository repository;

	@Transactional
	public User create(User user) {

		return repository.save(user);
	}

	@Transactional
	public User delete(Long personId){

		User deleted = repository.findOne(personId);

		repository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {
		return repository.findOne(id);
	}

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

	@Transactional
	public User update(User updated){

		User user = repository.findOne(updated.getId());

		user.setUsername(updated.getUsername());

		return user;
	}


	protected void setPersonRepository(UserRepository userRepository) {
		this.repository = userRepository;
	}
}