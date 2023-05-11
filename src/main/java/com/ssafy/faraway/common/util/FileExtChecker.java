package com.ssafy.faraway.common.util;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@Slf4j
public class FileExtChecker {
    private final String[] allowedExt = {"png", "jpg", "jpeg", "gif"};
    public void checkExtension(List<MultipartFile> multipartFiles) {
        boolean isValid = false;
        for (MultipartFile file : multipartFiles) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                int pos = originalFilename.lastIndexOf(".");
                String ext = originalFilename.substring(pos + 1);
                log.debug("확장자: {}", ext);
                for (String s : allowedExt) {
                    if (s.equals(ext)) {
                        isValid = true;
                        break;
                    }
                }
                if (!isValid) {
                    throw new CustomException(ErrorCode.BAD_REQUEST);
                }
            }
        }
    }
}
