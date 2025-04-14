package com.learningcrew.linkup.community.command.application.service;

import com.learningcrew.linkup.common.service.FileStorage;
import com.learningcrew.linkup.community.command.application.dto.PostImageResponse;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
import com.learningcrew.linkup.community.command.domain.repository.PostImageRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostRepository postRepository;  // 게시글 리포지토리
    private final PostImageRepository postImageRepository;  // 게시글 이미지 리포지토리
    private final FileStorage fileStorage;

    @Transactional
    public PostImageResponse updatePostImages(int postId, List<MultipartFile> postImgs) {
        // 1. 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        // 2. 기존 이미지 삭제 (필요한 경우)
        postImageRepository.deleteByPost_PostId(postId);

        // 3. 새 이미지 파일 저장
        List<String> imageUrls = new ArrayList<>();
        if (postImgs != null && !postImgs.isEmpty()) {
            for (MultipartFile file : postImgs) {
                if (!file.isEmpty()) {
                    String storedFilename = fileStorage.storeFile(file);
                    String imageUrl = "images/" + storedFilename;

                    PostImage postImage = new PostImage();
                    postImage.setPost(post);
                    postImage.setImageUrl(imageUrl);

                    postImageRepository.save(postImage);
                    imageUrls.add(imageUrl);
                }
            }
        }

        // 4. 응답 생성
        PostImageResponse response = new PostImageResponse();
        response.setImageUrls(imageUrls);
        return response;
    }
}