package ur.veli.webapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UserNamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.admindetails.AdminDetailsService;
import ur.veli.webapp.service.SecurityService;

public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AdminDetailsSevice adminDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUserName() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }
    @Override
    public String findLoggedInAdminName() {
        Object adminDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (adminDetails instanceof UserDetails) {
            return ((UserDetails)adminDetails).getUsername();
        }

        return null;
    }
    @Override
    public void autoLogin(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUserName(userName);
        UserNamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UserNamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(userNamePasswordAuthenticationToken);

        if (userNamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", userName));
        }
    }
    @Override
    public void autoLogin(String adminName, String password) {
        AdminDetails adminDetails = adminDetailsService.loadAdminByUserName(adminName);
        AdminNamePasswordAuthenticationToken adminNamePasswordAuthenticationToken = new AdminNamePasswordAuthenticationToken(adminDetails, password, adminDetails.getAuthorities());

        authenticationManager.authenticate(adminNamePasswordAuthenticationToken);

        if (adminNamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(adminNamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", adminName));
        }
    }
}
