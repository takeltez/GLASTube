package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.glastube.entity.Video;
import ru.glastube.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchVideoController {
    @Autowired
    private VideoRepository crudRep;

    @RequestMapping(value = "/resultSearch", method = RequestMethod.GET)
    public ModelAndView SearchPage(@RequestParam("text") String text) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Video> videos = new ArrayList<>();
        System.out.println(videos.size());
        for (Video video: crudRep.findAll()){
            if (video.getName().contains(text)) {
                videos.add(video);
            }
        }

        model.addObject("videos", videos);

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("login", userDetail.getUsername());
            model.addObject("signin_myprofile", "My profile");
            model.addObject("signup_signout", "Sign out");
            model.addObject("settings", "Settings");
        } else {
            model.addObject("login", ".O.");
            model.addObject("signin_myprofile", "Sign in");
            model.addObject("signup_signout", "Sign up");
        }
        model.setViewName("SearchPage");
        return model;
    }
}
