    package com.learningcrew.linkup.community.command.application.controller;

    import com.learningcrew.linkup.common.dto.ApiResponse;
    import com.learningcrew.linkup.common.service.FileStorage;
    import com.learningcrew.linkup.community.command.application.dto.PostCreateRequestDTO;
    import com.learningcrew.linkup.community.command.application.dto.PostUpdateRequestDTO;
    import com.learningcrew.linkup.community.command.application.service.PostCommandService;
    import com.learningcrew.linkup.community.command.domain.aggregate.Post;
    import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
    import com.learningcrew.linkup.community.command.domain.repository.PostImageRepository;
    import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
    import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
    import com.learningcrew.linkup.exception.BusinessException;
    import com.learningcrew.linkup.exception.ErrorCode;
    import com.learningcrew.linkup.exception.SuccessCode;
    import com.learningcrew.linkup.exception.SuccessResponseMessage;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import jakarta.transaction.Transactional;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;


    @RestController
        @RequiredArgsConstructor
        @RequestMapping("/api/v1")
        @Tag(name = "Post", description = "게시글")
    public class PostCommandController {

        private final PostRepository postRepository;
        private final PostImageRepository postImageRepository;
        private final FileStorage fileStorage; // 파일 업로드 처리를 위한 클래스
        private final ModelMapper modelMapper;
        private final PostCommandService postCommandService;


        // 게시글 생성 API
        @PostMapping("/posts")
        @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성하고, 관련된 이미지 파일을 업로드합니다.")
        @Transactional
        public ResponseEntity<ApiResponse<PostDetailResponse>> createPost(
                @RequestPart("postCreateRequestDTO") @Validated PostCreateRequestDTO postCreateRequestDTO,
                @RequestPart(required = false) List<MultipartFile> postImgs) {

            String mainImageFilename = "";

            // 이미지 파일이 있을 경우, 대표 이미지 저장
            if (postImgs != null && !postImgs.isEmpty()) {
                MultipartFile mainFile = postImgs.get(0); // 첫 번째 이미지를 대표 이미지로 설정
                if (!mainFile.isEmpty()) {
                    mainImageFilename = fileStorage.storeFile(mainFile);
                }
            }

            // 게시글 생성
            Post newPost = modelMapper.map(postCreateRequestDTO, Post.class);
            newPost.setMainImageUrl(mainImageFilename);

            // 게시글 DB에 저장
            Post savedPost = postRepository.save(newPost);
            int generatedPostId = savedPost.getPostId();

            // 이미지 파일 처리
            if (postImgs != null && !postImgs.isEmpty()) {
                for (MultipartFile file : postImgs) {
                    if (!file.isEmpty()) {
                        String storedFilename = fileStorage.storeFile(file);
                        PostImage postImage = new PostImage();
                        postImage.setPost(savedPost);
                        postImage.setImageUrl("images/" + storedFilename);
                        postImageRepository.save(postImage);
                    }
                }
            }

            // 성공적인 응답 반환
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
        }


            /* 2. 게시글 수정 */
        @PutMapping("/posts/{postId}")
        @Operation(summary = "게시글 수정", description = "게시글의 제목과 내용을 수정합니다.")
        @Transactional
        public ResponseEntity<ApiResponse<PostDetailResponse>> updatePost(
                @PathVariable int postId,
                @RequestPart("postUpdateRequestDTO") @Valid PostUpdateRequestDTO postUpdateRequestDTO,
                @RequestPart(required = false) List<MultipartFile> postImgs) {

            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

            post.updatePostDetails(
                    postUpdateRequestDTO.getUserId(),
                    postUpdateRequestDTO.getTitle(),
                    postUpdateRequestDTO.getContent(),
                    postUpdateRequestDTO.postIsNotice()
            );

            // 기존 이미지 삭제 및 새로 저장
            if (postImgs != null && !postImgs.isEmpty()) {
                postImageRepository.deleteByPost_PostId(postId);

                for (MultipartFile file : postImgs) {
                    if (!file.isEmpty()) {
                        String storedFilename = fileStorage.storeFile(file);
                        String imageUrl = "/images/" + storedFilename;

                        PostImage postImage = new PostImage();
                        postImage.setPost(post);
                        postImage.setImageUrl(imageUrl);
                        postImageRepository.save(postImage);
                    }
                }
            }

            return ResponseEntity.ok(ApiResponse.success(null));
        }


        /* 3. 게시글 삭제 */
        @PutMapping("/posts/{postId}/delete")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public ResponseEntity<ApiResponse<SuccessResponseMessage>> deletePost(
            @PathVariable int postId,
            @RequestParam int memberId) {

        // 게시글 조회 (게시글의 작성자 정보 가져오기)
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        int authorId = post.getUserId(); // 게시글 작성자 ID

        // 작성자만 삭제할 수 있도록 처리
        if (authorId != memberId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 권한이 없으면 403 Forbidden 응답
        }

        // 게시글 삭제 상태를 "Y"로 업데이트
        post.setIsDelete("Y");
        postRepository.save(post);  // 상태 변경을 데이터베이스에 저장

        // 성공적인 응답 반환
        SuccessResponseMessage response = new SuccessResponseMessage(SuccessCode.POST_DELETE_SUCCESS);
        return ResponseEntity.ok(ApiResponse.success(response)); // ApiResponse를 통해 응답 반환
    }
    }