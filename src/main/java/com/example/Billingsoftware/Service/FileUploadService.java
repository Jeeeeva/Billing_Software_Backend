package com.example.Billingsoftware.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile File);
    Boolean deleteFile(String imgUrl);
}
