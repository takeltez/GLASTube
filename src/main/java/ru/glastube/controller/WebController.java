package ru.glastube.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.glastube.entity.Comments;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexStart() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signup_signout", "Sign out");
        } else {
            model.addObject("username", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.setViewName("indexStart");
        return model;
    }

    @RequestMapping(value = "/signin_myprofile", method = RequestMethod.GET)
    public ModelAndView signInMyProfile() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("nickname", userDetail.getUsername());
            System.out.println(userDetail.getUsername());
//            model.addObject("myprofile", "My profile");
//            model.addObject("signout", "Sign out");
            model.setViewName("indexMainPage");
            return model;
        } else {
            model.setViewName("Login");
            return model;
        }
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

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("comment",new Comments());
        return "comments";
    }
}
