package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.glastube.entity.User;
import ru.glastube.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/register")
    public User register(@RequestParam("nickname") String nickname, @RequestParam("login") String login, @RequestParam("password") String password) {
        password = new StandardPasswordEncoder().encode(password);
        User user = null;
        user = crudRep.findByLogin(login);

        if (user != null) {
            //System.out.println("This login already exist!");
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

}
