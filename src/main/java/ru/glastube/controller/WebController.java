package ru.glas***.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.glas***.entity.User;
import ru.glas***.repository.UserRepository;

@Controller
public class WebController {

    @Autowired
    private UserRepository crudRep;

    @RequestMapping("/")
    public String indexStart(Model model) {
        model.addAttribute("name", "Welcome, You!");

        return "indexStart";
    }
    @RequestMapping("/sign_up")
    public String indexSignUp(Model model) {
        model.addAttribute("name", "Sign Up");

        return "indexSignUp";
    }
    @RequestMapping("/sign_in")
    public String indexMainPage(Model model) {
        model.addAttribute("name", "Home Page");
        return "indexMainPage";
    }
}
