package ur.veli.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ur.veli.webapp.models.Admin;
import ur.veli.webapp.repository.AdminRep;
import ur.veli.webapp.repository.RoleRep;
import ur.veli.webapp.service.AdminService;

import java.util.HashSet;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRep adminRep;
    @Autowired
    private RoleRep roleRep;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setRoles(new HashSet<>(roleRep.findAll()));
        adminRep.save(admin);
    }

    @Override
    public Admin findByAdminName(String adminname) {
        return adminRep.findByAdminName(adminname);
    }
}
