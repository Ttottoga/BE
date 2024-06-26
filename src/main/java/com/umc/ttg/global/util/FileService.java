package com.umc.ttg.global.util;

import com.umc.ttg.global.common.AwsS3;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    Object upload(MultipartFile multipartFile, String directoryName) throws IOException;

    void remove(Object object);
}
