package ur.veli.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ur.veli.webapp.models.Role;

public interface RoleRep extends JpaRepository<Role, Long>{
}
