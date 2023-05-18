package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.controller.dto.req.SavePostCommentRequest;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.entity.PostComment;
import com.ssafy.faraway.domain.post.repository.PostCommentRepository;
import com.ssafy.faraway.domain.post.service.PostCommentService;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {
    private final PostCommentRepository postCommentRepository;

    @Override
    public Long save(Long postId, Long memberId, SavePostCommentRequest request) {
        PostComment postComment = PostComment.builder()
                .content(request.getContent())
                .member(Member.builder().id(memberId).build())
                .post(Post.builder().id(postId).build())
                .build();
        return postCommentRepository.save(postComment).getId();
    }

    @Override
    public Long update(Long commentId, UpdatePostCommentDto dto) {
        PostComment postComment = postCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        postComment.update(dto.getContent());
        return commentId;
    }

    @Override
    public Long delete(Long commentId) {
        PostComment postComment = postCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        postCommentRepository.delete(postComment);
        return commentId;
    }
}
