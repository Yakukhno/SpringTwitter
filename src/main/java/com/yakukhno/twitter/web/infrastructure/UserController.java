package com.yakukhno.twitter.web.infrastructure;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yakukhno.twitter.domain.User;
import com.yakukhno.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

//    @PostMapping(value = "/create")
//    public String createUser(User user){
//        userService.addUser(user);
//        return "redirect:";
//    }

    @GetMapping(value = {"/create", "/update"})
    public String user(){
        return "createUser";
    }

//    @PostMapping(value = "/{id}/update")
//    public String updateUser(@ModelAttribute User user){
//        userService.updateUser(user);
//        return "redirect:/web/user";
//    }

//    @GetMapping(value = "/{id}/update")
//    public String updateUserPage(@PathVariable int id, Model model){
//        model.addAttribute("operation", "update");
//        model.addAttribute("user", userService.getUser(id).get());
//        return "createUser";
//    }

    @PostMapping(value = "/createUpdate")
    public String createUpdate(@ModelAttribute("user") User user, BindingResult result) {
        System.out.println("u = " + user);
        userService.addUser(user);
        return "redirect:/web/user";
    }

    @ModelAttribute("user")
    private User user(@RequestParam(value = "id", required = false) User user) {
        System.out.println("model user = " + user);
        return user;
    }

    @InitBinder
    public void userBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String s) throws IllegalArgumentException {
                User user;
                if (s == null || s.isEmpty()) {
                    user = new User();
                } else {
                    user = userService.getUser(Integer.parseInt(s)).orElse(null);
                }
                setValue(user);
            }
        });
    }
}