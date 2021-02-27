package ur.veli.webapp.service;

import ur.veli.webapp.models.User;

public interface UserService {
    void save(User user);

    User findByUserName(String username);
}
