package ru.glastube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glastube.entity.Comments;
import ru.glastube.entity.Video;
import ru.glastube.repository.CommentRepository;
import ru.glastube.repository.VideoRepository;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    VideoRepository videoRep;

    @RequestMapping("/all_comments")
    public List<Comments> getComments(@RequestParam("videoname") String videoname) {
        Integer id = videoRep.findByName(videoname).getId();
        Video v = videoRep.findById(id).get();
        List<Comments> comments = v.getComments();
        return comments;

    }

    @RequestMapping("/add_comment")
    public Comments addComment(@RequestParam("videoname") String videoname,
                               @RequestParam("user") String user,
                               @RequestParam("text") String text){
        Video video = videoRep.findByName(videoname);
        String date = new java.util.Date().toString();
        String [] temp = date.split(" ");
        String new_date = temp[2] + " " + temp[1] + " " + temp[5] + " " + temp[3];
        System.out.println(user + " " + new_date + " " + text);
        return commentRepository.save(new Comments(user, new_date, text, video));
    }
}
