package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping("/upload/csv")
    public ResponseEntity<String> saveCsvFileToDatabase(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(!fileService.hasCSVFormat(multipartFile)) return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Please upload CSV file");
        fileService.processAndSaveData(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
