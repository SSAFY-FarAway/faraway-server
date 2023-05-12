package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.domain.post.entity.Attachment;
import com.ssafy.faraway.domain.post.repository.AttachmentRepository;
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
@RequestMapping("/attachment")
public class AttachmentController {
    private final AttachmentRepository attachmentRepository;

    @Value("${file.dir}")
    public String fileDir;

    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) throws IOException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Path saveFilePath = Paths.get(fileDir + attachment.getUploadFile().getStoreFileName());

        if (!saveFilePath.toFile().exists()) {
            throw new RuntimeException("File Not Found");
        }

        setFileHeader(response, attachment);

        fileCopy(response, saveFilePath);
    }

    private void setFileHeader(HttpServletResponse response, Attachment attachment) throws UnsupportedEncodingException {
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(attachment.getUploadFile().getUploadFileName(), "UTF-8") + "\";");
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
