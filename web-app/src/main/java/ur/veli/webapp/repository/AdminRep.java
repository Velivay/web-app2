package ur.veli.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ur.veli.webapp.models.Admin;

public interface AdminRep extends JpaRepository<Admin, Long> {
    Admin findByAdminName(String adminName);
}
