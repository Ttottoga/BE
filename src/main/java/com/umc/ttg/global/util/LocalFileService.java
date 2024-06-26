package com.umc.ttg.global.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LocalFileService implements FileService {
    @Override
    public Object upload(MultipartFile multipartFile, String directoryName) throws IOException {

        Path uploadPath = Paths.get(directoryName);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        // 파일 저장
        Path filePath = uploadPath.resolve(Objects.requireNonNull(
                                        multipartFile.getOriginalFilename()));

        Files.copy(multipartFile.getInputStream(), filePath);

        // 저장한 경로 반환
        return filePath.toString();
    }

    @Override
    public void remove(Object object) throws IOException {

        String path = (String) object;

        Path filePath = Paths.get(path);
        Files.deleteIfExists(filePath);
    }
}
