package ur.veli.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ur.veli.webapp.models.Admin;
import ur.veli.webapp.models.User;
import ur.veli.webapp.service.AdminService;
import ur.veli.webapp.service.SecurityService;
import ur.veli.webapp.validator.AdminValidator;

public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AdminValidator adminValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("adminForm", new Admin());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("AdminForm") Admin adminForm, BindingResult bindingResult) {
        adminValidator.validate(adminForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        adminService.save(adminForm);

        securityService.autoLogin(adminForm.getAdminName(), adminForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("UserForm") User userForm, BindingResult bindingResult){
        adminValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        return "edit";
    }
        adminService.edit(userForm);

        securityService.autoLogin(userForm.getUserName(), userForm.getUserName());
    }



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
