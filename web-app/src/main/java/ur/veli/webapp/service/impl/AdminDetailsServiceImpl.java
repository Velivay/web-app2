package ur.veli.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.admindetails.AdminNameNotFoundException;
import ur.veli.webapp.models.Admin;
import ur.veli.webapp.models.Role;
import ur.veli.webapp.repository.AdminRep;

import java.util.HashSet;
import java.util.Set;
@Service
public class AdminDetailsServiceImpl {
    @Autowired
    private AdminRep adminRep;

    @Override
    @Transactional(readOnly = true)
    public AdminDetails loadAdminByAdminName(String adminName) {
        Admin admin = adminRep.findByAdminName(adminName);
        if (admin == null) throw new AdminNameNotFoundException(adminName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : admin.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.admindetails.Admin(admin.getAdminName(),adminName.getPassword(), grantedAuthorities);
    }
}
