package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
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

    @GetMapping("/display/{id}")
    public Resource display(@PathVariable Long id, HttpServletResponse response) throws IOException {
        HotPlaceImage hotPlaceImage = hotPlaceImageRepository.findById(id).orElseThrow(NoSuchElementException::new);
        String fullPath = fileDir + hotPlaceImage.getUploadFile().getStoreFileName();
        Resource resource = new FileSystemResource(fullPath);
        if (!resource.exists()) {
            throw new RuntimeException("File Not Found");
        }
        Path imagePath = Paths.get(fullPath);
        response.setHeader("Content-Type", Files.probeContentType(imagePath));
        return resource;
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
