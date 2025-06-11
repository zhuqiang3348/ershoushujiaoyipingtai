package com.service;


import com.entity.BookTag;
import com.entity.UserTag;
import repository.BookTagRepository;
import repository.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private BookTagRepository bookTagRepo;

    @Autowired
    private UserTagRepository userTagRepo;

    public BookTag addBookTag(Long bookId, String tag) {
        BookTag bookTag = new BookTag();
        bookTag.setBookId(bookId);
        bookTag.setTag(tag);
        return bookTagRepo.save(bookTag);
    }

    public List<BookTag> getBookTags(Long bookId) {
        return bookTagRepo.findByBookId(bookId);
    }

    public UserTag addUserTag(Long userId, String tag, Double score) {
        UserTag userTag = new UserTag();
        userTag.setUserId(userId);
        userTag.setTag(tag);
        userTag.setScore(score);
        return userTagRepo.save(userTag);
    }

    public List<UserTag> getUserTags(Long userId) {
        return userTagRepo.findByUserId(userId);
    }
}
