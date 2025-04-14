package com.learningcrew.linkup.community.command.application.service;

import com.learningcrew.linkup.community.command.application.dto.PostUpdateRequestDTO;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
import com.learningcrew.linkup.community.command.domain.constants.PostIsDeleted;
import com.learningcrew.linkup.community.command.domain.constants.PostIsNotice;
import com.learningcrew.linkup.community.command.domain.repository.PostImageRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.exception.SuccessCode;
import com.learningcrew.linkup.exception.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;


    /* 1. 게시글과 함께 사진 파일 등록 */
    @Transactional
    public ResponseEntity<SuccessResponseMessage> createPostWithImages(
            int userId,
            String title,
            String content,
            String postIsDeleted,
            String postIsNotice,
            List<String> imageUrls) {


        // 게시글 제목 검증
        if (title == null || title.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.TITLE_EMPTY);
        }

        // 게시글 내용 검증
        if (content == null || content.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.CONTENT_EMPTY);
        }

        // 이미지 URL 목록 검증
        if (imageUrls == null || imageUrls.isEmpty()) {
            throw new BusinessException(ErrorCode.IMAGE_URLS_EMPTY);
        }


        // 게시글 생성
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .postIsDeleted(PostIsDeleted.valueOf(postIsDeleted))
                .postIsNotice(PostIsNotice.valueOf(postIsNotice))
                .build();


        // 이미지 첨부
        for (String url : imageUrls) {
            PostImage image = PostImage.builder()
                    .imageUrl(url)
                    .build();

            post.addImage(image);

            postImageRepository.save(image);  // 이미지 저장
        }

        // 성공 응답 생성
        SuccessResponseMessage response = new SuccessResponseMessage(
                SuccessCode.POST_CREATE_SUCCESS
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 2. 게시글 수정 */
    @Transactional
    public ResponseEntity<SuccessResponseMessage> updatePost(int postId, PostUpdateRequestDTO postUpdateRequestDTO) {
        // 1. 게시글 조회
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        // 2. 수정된 게시글 정보로 업데이트
        post.updatePostDetails(
                String.valueOf(postUpdateRequestDTO.getUserId()),
                postUpdateRequestDTO.getTitle(),
                postUpdateRequestDTO.getContent()
        );

        // 3. 게시글 업데이트
        postRepository.save(post);

        // 4. 성공 응답 생성
        SuccessResponseMessage response = new SuccessResponseMessage(
                SuccessCode.POST_UPDATE_SUCCESS.getHttpStatus(),
                SuccessCode.POST_UPDATE_SUCCESS.getMessage()
                );

        return ResponseEntity.ok(response);
    }

    /* 3. 게시글 삭제 */
    @Transactional
    public void deletePost(int postId) {
        // 1. 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        // 2. 게시글 삭제 처리 (soft delete로 처리할 수도 있음)
        post.setPostIsDeleted(PostIsDeleted.Y);  // soft delete (삭제된 게시글로 표시)
        postRepository.save(post);  // 변경사항 저장
    }
}