package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final HotPlaceImageRepository hotPlaceImageRepository;

    @Value("${file.dir}")
    public String fileDir;

    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) throws IOException {
        HotPlaceImage hotPlaceImage = hotPlaceImageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Path saveFilePath = Paths.get(fileDir + hotPlaceImage.getUploadFile().getStoreFileName());

        if (!saveFilePath.toFile().exists()) {
            throw new RuntimeException("File Not Found");
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
