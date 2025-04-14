package com.learningcrew.linkup.community.command.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PostImageService {

    private final String uploadDirectory = "uploads/";

    // 파일 업로드 메서드
    public String uploadFile(MultipartFile file) throws IOException {
        // 파일이 비어있는지 체크
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        // 파일 경로 설정
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        Path filePath = Paths.get(uploadDirectory, uniqueFileName);

        // 디렉토리 생성 (없으면)
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // 파일 저장
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new IOException("Failed to upload file", e);
        }

        // 파일 URL 반환
        // 예를 들어, 로컬 서버에서 파일이 "uploads" 폴더에 저장된다면 URL은 아래와 같습니다.
        return "/uploads/" + uniqueFileName;
    }

    // 파일 삭제 메서드 (옵션)
    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
