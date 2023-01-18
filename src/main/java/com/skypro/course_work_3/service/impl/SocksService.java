package com.skypro.course_work_3.service.impl;

import com.skypro.course_work_3.dto.DtoSocks;
import com.skypro.course_work_3.model.Color;
import com.skypro.course_work_3.model.Size;
import com.skypro.course_work_3.model.Socks;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface SocksService {
    void validateRequest(DtoSocks dtoSocks);

    void addSocks(DtoSocks dtoSocks);

    Socks mapSocks(DtoSocks dtoSocks);

    void reduceQuantitySocks(DtoSocks dtoSocks);

    int getQuantity(Color color, Size size, Integer cottonPartMin, Integer cottonPartMax);

    FileSystemResource exportData() throws IOException;

    File getDataFile();

    boolean cleanDataFile();
}
