package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final HotPlaceImageRepository hotPlaceImageRepository;

    @Value("${file.dir}")
    public String fileDir;

    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) throws IOException {
        HotPlaceImage hotPlaceImage = hotPlaceImageRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.IMAGE_NOT_FOUND));
        Path saveFilePath = Paths.get(fileDir + hotPlaceImage.getUploadFile().getStoreFileName());

        if (!saveFilePath.toFile().exists()) {
            throw new FileNotFoundException();
        }

        setFileHeader(response, hotPlaceImage);

        fileCopy(response, saveFilePath);
    }

    private void setFileHeader(HttpServletResponse response, HotPlaceImage hotPlaceImage) throws UnsupportedEncodingException {
        response.setHeader("Content-Disposition", "hotPlaceImage; filename=\"" + URLEncoder.encode(hotPlaceImage.getUploadFile().getUploadFileName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", "application/download; utf-8");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
    }

    private void fileCopy(HttpServletResponse response, Path saveFilePath) throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream(saveFilePath.toFile());
        FileCopyUtils.copy(fis, response.getOutputStream());
        response.getOutputStream().flush();
        fis.close();
    }
}
