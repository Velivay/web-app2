package ur.veli.webapp.service;

public interface SecurityService {
    String findLoggedInUserName();
    String findLoggedInAdminName();

    void autoLogin(String  Name, String password);
}
