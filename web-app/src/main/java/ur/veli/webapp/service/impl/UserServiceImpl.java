package ur.veli.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ur.veli.webapp.models.User;
import ur.veli.webapp.repository.RoleRep;
import ur.veli.webapp.repository.UserRep;
import ur.veli.webapp.service.UserService;

import java.util.HashSet;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRep userRepository;
    @Autowired
    private RoleRep roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
