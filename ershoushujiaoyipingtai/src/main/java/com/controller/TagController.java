package com.controller;



import com.entity.BookTag;
import com.entity.UserTag;
import com.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/book")
    public BookTag addBookTag(@RequestParam Long bookId, @RequestParam String tag) {
        return tagService.addBookTag(bookId, tag);
    }

    @GetMapping("/book/{bookId}")
    public List<BookTag> getBookTags(@PathVariable Long bookId) {
        return tagService.getBookTags(bookId);
    }

    @PostMapping("/user")
    public UserTag addUserTag(@RequestParam Long userId, @RequestParam String tag, @RequestParam(defaultValue = "1.0") Double score) {
        return tagService.addUserTag(userId, tag, score);
    }

    @GetMapping("/user/{userId}")
    public List<UserTag> getUserTags(@PathVariable Long userId) {
        return tagService.getUserTags(userId);
    }
}
