package service.user;

import model.User;
import model.validator.Notification;
import repository.user.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Notification<User> findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean save(User user){
        return userRepository.save(user);
    }

    public void removeAll(){
        userRepository.removeAll();
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public Notification<User> findById(Long id){
        return userRepository.findById(id);
    }

    public boolean updateUser(User user){
        return userRepository.updateUser(user);
    }

    public boolean removeById(Long id){
        return userRepository.removeById(id);
    }
}
