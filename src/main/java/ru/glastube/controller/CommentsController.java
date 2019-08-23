package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glastube.entity.Comments;
import ru.glastube.repository.CommentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;


    @RequestMapping("/all_comments")
    public List<Comments> getComments() {
        List<Comments> cms = new ArrayList<>();
        commentRepository.findAll().forEach(cms::add);
        return cms;
    }

    @RequestMapping("/add_comment")
    public Comments addComment(@RequestParam("id_user") Integer id_user,
                               @RequestParam("text") String text){
        Date date = new java.util.Date();
        System.out.println(id_user + " " + date + " " + text);
        return commentRepository.save(new Comments(id_user, date, text));
    }
}
