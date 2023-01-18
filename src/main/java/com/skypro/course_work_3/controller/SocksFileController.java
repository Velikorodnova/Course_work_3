package com.skypro.course_work_3.controller;

import com.skypro.course_work_3.dto.DtoSocks;
import com.skypro.course_work_3.service.impl.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class SocksFileController {
    private final SocksService socksService;

    public SocksFileController(SocksService socksService) {
        this.socksService = socksService;
    }

    @Operation(summary = "Экспортировать файл")
    @GetMapping("/export/socks")
    public FileSystemResource downloadDataFile() throws IOException {
        return socksService.exportData();
    }

    @Operation(summary = "Импортировать файл")
    @PutMapping(value = "/import/socks")
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        socksService.cleanDataFile();
        File dataFile = socksService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
