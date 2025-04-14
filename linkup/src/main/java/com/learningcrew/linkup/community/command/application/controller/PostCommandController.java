package com.learningcrew.linkup.community.command.application.controller;

import com.learningcrew.linkup.common.service.FileStorage;
import com.learningcrew.linkup.community.command.application.dto.PostCreateRequestDTO;
import com.learningcrew.linkup.community.command.application.dto.PostUpdateRequestDTO;
import com.learningcrew.linkup.community.command.application.service.PostCommandService;
import com.learningcrew.linkup.community.command.application.service.PostImageService;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
import com.learningcrew.linkup.community.command.domain.repository.PostImageRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.exception.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/v1/posts")
    @Tag(name = "Post", description = "게시글")
    public class PostCommandController {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final FileStorage fileStorage;  // 파일 업로드 처리를 위한 클래스
    private final ModelMapper modelMapper;
    private final PostCommandService postCommandService;



    /* 1. 게시글 및 사진 파일 등록 */
    @PostMapping("")
    @Operation(summary = "게시글 및 사진 파일 등록", description = "게시글을 등록하고, 게시글과 관련된 사진 파일을 등록합니다.")
        @Transactional
        public int createPost(PostCreateRequestDTO postCreateRequestDTO, List<MultipartFile> postImgs) {
            String mainImageFilename = "";
            // 이미지 파일 리스트가 있다면
            if (postImgs != null && !postImgs.isEmpty()) {
                // 대표 이미지로 첫 번째 파일 선택 (원하는 방식에 따라 다르게 처리 가능)
                MultipartFile mainFile = postImgs.get(0);
                if (!mainFile.isEmpty()) {
                    mainImageFilename = fileStorage.storeFile(mainFile);
                }
            }

            // 게시글 엔티티 생성 (DTO -> Entity 매핑)
            Post newPost = modelMapper.map(postCreateRequestDTO, Post.class);

            // 게시글 INSERT 및 생성된 postId 획득
            Post savedPost = postRepository.save(newPost);
            int generatedPostId = savedPost.getPostId();

            // 게시글에 포함된 이미지 파일들을 처리
            if (postImgs != null && !postImgs.isEmpty()) {
                for (MultipartFile file : postImgs) {
                    if (!file.isEmpty()) {
                        // 파일 저장 (대표 이미지는 이미 저장되어 있을 수 있으므로, 중복 저장을 피하고 싶으면 조건 처리 가능)
                        String storedFilename = fileStorage.storeFile(file);
                        PostImage postImage = new PostImage();
                        postImage.setPostId(generatedPostId);
                        postImage.setImageUrl(postImage.getImageUrl() + storedFilename);
                        postImageRepository.save(postImage);
                    }
                }
            }

            return generatedPostId;
        }

    /* 2. 게시글 수정 */
    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "게시글의 제목과 내용을 수정합니다.")
    @Transactional
    public void updatePost(int postId, PostUpdateRequestDTO postUpdateRequestDTO, List<MultipartFile> postImgs) {
        // 게시글 존재 여부 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        // 게시글 업데이트 (DTO -> Entity 매핑)
        post.updatePostDetails(
                postUpdateRequestDTO.getTitle(),
                postUpdateRequestDTO.getContent(),
                postUpdateRequestDTO.postIsNotice()
        );

        // 이미지 파일이 있다면 업데이트
        if (postImgs != null && !postImgs.isEmpty()) {
            // 기존 이미지 파일 삭제 (필요한 경우)
            postImageRepository.deleteByPost_PostId(postId);

            // 새로운 이미지 파일 저장
            for (MultipartFile file : postImgs) {
                if (!file.isEmpty()) {
                    String storedFilename = fileStorage.storeFile(file);
                    PostImage postImage = new PostImage();
                    postImage.setPostId(postId);
                    postImage.setImageUrl(storedFilename);
                    postImageRepository.save(postImage);
                }
            }
        }
    }


    /* 3. 게시글 삭제 */
    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public ResponseEntity<SuccessResponseMessage> deletePost(@PathVariable int postId) {
        // 게시글 삭제 서비스 호출
        postCommandService.deletePost(postId);

        // 삭제 성공 메시지 응답
        SuccessResponseMessage response = new SuccessResponseMessage(
                SuccessCode.POST_DELETE_SUCCESS
        );

        return ResponseEntity.ok(response);
    }
}