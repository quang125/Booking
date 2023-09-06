package com.example.bookingmeetingroom.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public boolean hasCSVFormat(MultipartFile file);
    public void processAndSaveData(MultipartFile file) throws IOException;
}
