package service.user;

import model.User;
import model.validator.Notification;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    boolean existsByUsername(String username);

     Notification<User> findById(Long id);

     boolean updateUser(User user);

     boolean removeById(Long id);
}
