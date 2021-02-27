package ur.veli.webapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ur.veli.webapp.models.Admin;
import ur.veli.webapp.models.User;
import ur.veli.webapp.service.AdminService;

@Component
public class AdminValidator  implements Validator {
        @Autowired
        private AdminService adminService;

        @Override
        public boolean supports(Class<?> aClass) {
            return Admin.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            Admin admin = (Admin) o;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adminName", "NotEmpty");
            if (admin.getAdminName().length() < 6 || admin.getAdminName().length() > 32) {
                errors.rejectValue("adminName", "Size.adminForm.adminName");
            }
            if (adminService.findByAdminName(admin.getAdminName()) != null) {
                errors.rejectValue("adminName", "Duplicate.adminForm.adminName");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (admin.getPassword().length() < 8 || admin.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.adminForm.password");
            }

            if (!admin.getPasswordConfirm().equals(admin.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.adminForm.passwordConfirm");
            }
        }
    }
}
