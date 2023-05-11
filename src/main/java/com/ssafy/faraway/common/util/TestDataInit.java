package com.ssafy.faraway.common.util;

import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.entity.PostComment;
import com.ssafy.faraway.domain.post.repository.CategoryRepository;
import com.ssafy.faraway.domain.post.repository.PostCommentRepository;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final CategoryRepository categoryRepository;
    private final HotPlaceRepository hotPlaceRepository;

    @PostConstruct
    public void init() {
        Long memberId = createMember();
        Long categoryId = createCategory();

        initPost(memberId, categoryId);
        initHotPlace(memberId);
    }

    private Long createMember() {
        Name memberName = Name.builder()
                .lastName("김")
                .firstName("싸피")
                .build();

        Address memberAddress = Address.builder()
                .zipcode("11111")
                .mainAddress("광주광역시 수완동")
                .subAddress("1526")
                .build();

        Member member = Member.builder()
                .loginId("ssafy")
                .loginPwd("1234")
                .name(memberName)
                .birth("970804")
                .email("ssafy@ssafy.com")
                .address(memberAddress)
                .salt("salt")
                .role(Role.GUEST)
                .build();
        return memberRepository.save(member).getId();
    }

    private Long createCategory() {
        Category category = Category.builder()
                .categoryName("Notice")
                .build();
        return categoryRepository.save(category).getId();
    }

    private void initPost(Long memberId, Long categoryId) {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<PostComment> postComments = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            String title = String.format("Post Test Title %d", i);
            String content = String.format("Post Test Content %d", i);
            Post post = Post.builder()
                    .title(title)
                    .content(content)
                    .member(Member.builder().id(memberId).build())
                    .category(Category.builder().id(categoryId).build())
                    .build();
            posts.add(post);
        }
        postRepository.saveAll(posts);

        for (Post post : posts) {
            for (int j = 1; j <= 5; j++) {
                String content = String.format("Comment Test Content %d", j);
                PostComment postComment = PostComment.builder()
                        .content(content)
                        .member(Member.builder().id(memberId).build())
                        .post(Post.builder().id(post.getId()).build())
                        .build();
                postComments.add(postComment);
            }
        }
        postCommentRepository.saveAll(postComments);
    }

    private void initHotPlace(Long memberId) {
        ArrayList<HotPlace> hotPlaces = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            String title = String.format("HotPlace Test Title %d", i);
            String content = String.format("HotPlace Test Content %d", i);
            Random random = new Random();
            int rating = random.nextInt(6);
            com.ssafy.faraway.domain.hotplace.entity.Address address = com.ssafy.faraway.domain.hotplace.entity.Address.builder()
                    .zipcode("11111")
                    .mainAddress("광주광역시 수완동")
                    .subAddress("1526")
                    .build();

            HotPlace hotPlace = HotPlace.builder()
                    .title(title)
                    .content(content)
                    .address(address)
                    .member(Member.builder().id(memberId).build())
                    .rating(rating)
                    .build();
            hotPlaces.add(hotPlace);
        }
        hotPlaceRepository.saveAll(hotPlaces);
    }
}