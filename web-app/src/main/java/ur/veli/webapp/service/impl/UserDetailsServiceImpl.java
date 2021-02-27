package ur.veli.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserNameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ur.veli.webapp.models.Role;
import ur.veli.webapp.models.User;
import ur.veli.webapp.repository.UserRep;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserDetailsServiceImpl {
    @Autowired
    private UserRep userRep;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUserName(String userName) {
        User user = userRep.findByUserName(userName);
        if (user == null) throw new UserNameNotFoundException(userName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
