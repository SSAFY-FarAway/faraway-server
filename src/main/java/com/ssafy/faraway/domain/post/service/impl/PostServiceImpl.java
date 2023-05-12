package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.dto.req.SavePostRequest;
import com.ssafy.faraway.domain.post.dto.req.UpdatePostRequest;
import com.ssafy.faraway.domain.post.entity.Attachment;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.AttachmentRepository;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import com.ssafy.faraway.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Long save(SavePostRequest request, Long memberId, List<UploadFile> uploadFiles) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(Category.builder().id(request.getCategoryId()).build())
                .member(Member.builder().id(memberId).build())
                .build();
        Long saveId = postRepository.save(post).getId();

        List<Attachment> attachments = getAttachments(saveId, uploadFiles);
        attachmentRepository.saveAll(attachments);
        return saveId;
    }

    private List<Attachment> getAttachments(Long postId, List<UploadFile> uploadFiles) {
        if (uploadFiles.isEmpty()) {
            return new ArrayList<>();
        }
        return uploadFiles.stream()
                .map(uploadFile -> Attachment.builder()
                        .post(Post.builder().id(postId).build())
                        .uploadFile(uploadFile).build()
                ).collect(Collectors.toList());
    }

    @Override
    public Long update(Long postId, UpdatePostRequest request, List<UploadFile> uploadFiles) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        List<Attachment> attachments = getAttachments(postId, uploadFiles);
        post.update(request.getTitle(), request.getContent(), attachments);
        return postId;
    }

    @Override
    public Long delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        postRepository.delete(post);
        return postId;
    }
}
