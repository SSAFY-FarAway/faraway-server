package com.ssafy.faraway.common.util;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileExtChecker {
    private final String[] allowedExt = {"png", "jpg", "jpeg", "gif"};
    public void checkExtension(List<MultipartFile> multipartFiles) {
        for (MultipartFile file : multipartFiles) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                int pos = originalFilename.lastIndexOf(".");
                String ext = originalFilename.substring(pos + 1);
                for (String s : allowedExt) {
                    if (!s.equals(ext)) {
                        throw new CustomException(ErrorCode.BAD_REQUEST);
                    }
                }
            }
        }
    }
}
