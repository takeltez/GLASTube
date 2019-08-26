package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.glas***.entity.User;
import ru.glas***.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/register")
    public User register(@RequestParam("nickname") String nickname, @RequestParam("login") String login, @RequestParam("password") String password) {
        password = new StandardPasswordEncoder().encode(password);
        User user = crudRep.findByLogin(login);

        if (user != null) {
            return null;
        }
        return crudRep.save(new User(nickname, login, password, 1));
    }

    @RequestMapping("/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        crudRep.findAll().forEach(users::add);
        return users;
    }
    @RequestMapping(value = "/user_profile", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam("nickname") String nickname) {
        User user = crudRep.findByNickname(nickname);
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("nickname", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signout_signup", "Sign out");
            //return "Us";
        } else {
            model.addObject("nickname", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.addObject("user", user.getNickname());
        model.setViewName("UserProfile");
        return model;
    }

}
