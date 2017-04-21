package com.yakukhno.twitter.web.infrastructure;

import com.yakukhno.twitter.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@ModelAttribute User user){
        System.out.println("Mapping");
        return user.toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String user(){
        return "createUser";
    }

}