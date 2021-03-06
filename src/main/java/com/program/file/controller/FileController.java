package com.program.file.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.program.file.model.FileInfoEntity;
import com.program.file.model.UploadFileResponse;
import com.program.file.service.FileStorageService;

@RestController
@RequestMapping("/rest/api/file")
public class FileController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/upload-file")
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    FileInfoEntity dbFile = fileStorageService.storeFile(file);
    LOGGER.info("upload a file");
    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/rest/api/file/download-file/")
        .path(dbFile.getId()).toUriString();

    return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
  }

  @PostMapping("/upload-files")
  public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    LOGGER.info("upload a files");
    return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
  }

  @GetMapping("/download-file/{fileId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    LOGGER.info("download a files");
    // Load file from database
    FileInfoEntity dbFile = fileStorageService.getFile(fileId);

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
        .body(new ByteArrayResource(dbFile.getData()));
  }

}
