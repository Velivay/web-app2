package ur.veli.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ur.veli.webapp.models.User;

public interface UserRep extends JpaRepository<User, Long> {
        User findByUserName(String username);

}
