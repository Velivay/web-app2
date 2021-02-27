package ur.veli.webapp.service;

import ur.veli.webapp.models.Admin;
import ur.veli.webapp.models.User;

public interface AdminService {
    void save(Admin admin);

    Admin findByAdminName(String adminName);

    void edit(User userForm);
}
