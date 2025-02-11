package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.CommentResponse;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    CommentResponse getCommentsByPostId(long postId, int pageNo, int pageSize, String sortBy, String sortDir);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
