package com.skypro.course_work_3.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.course_work_3.dto.DtoSocks;
import com.skypro.course_work_3.exception.AddingError;
import com.skypro.course_work_3.exception.ElementNotFound;
import com.skypro.course_work_3.exception.FileError;
import com.skypro.course_work_3.model.Color;
import com.skypro.course_work_3.model.Size;
import com.skypro.course_work_3.model.Socks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class SocksServiceImpl implements SocksService {
    private final ObjectMapper objectMapper;
    private final Map<Socks, Integer> socksMap = new HashMap<>();

    public SocksServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public void validateRequest(DtoSocks dtoSocks) {
        if (dtoSocks.getColor() == null || dtoSocks.getSize() == null) {
            throw new AddingError("Все поля должны быть заполнены");
        }
        if (dtoSocks.getCottonPart() < 0 || dtoSocks.getCottonPart() > 100) {
            throw new AddingError("Процент хлопка должен быть в диапазоне от 0 до 100");
        }
        if (dtoSocks.getQuantity() <= 0) {
            throw new AddingError("Количество товара должно быть больше 0");
        }
    }
    @Override
    public void addSocks(DtoSocks dtoSocks) {
        validateRequest(dtoSocks);
        Socks socks = mapSocks(dtoSocks);
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + dtoSocks.getQuantity());
        } else {
            socksMap.put(socks, dtoSocks.getQuantity());
        }
    }
    @Override
    public Socks mapSocks(DtoSocks dtoSocks) {
        return new Socks(dtoSocks.getColor(), dtoSocks.getSize(), dtoSocks.getCottonPart());
    }
    @Override
    public void reduceQuantitySocks(DtoSocks dtoSocks) {
        validateRequest(dtoSocks);
        Socks socks = mapSocks(dtoSocks);
        int socksQuantity = socksMap.getOrDefault(socks, 0);
        if (socksQuantity >= dtoSocks.getQuantity()) {
            socksMap.put(socks, socksQuantity - dtoSocks.getQuantity());
        } else {
            throw new ElementNotFound("Товар не добавлен");
        }
    }
    @Override
    public int getQuantity(Color color, Size size, Integer cottonPartMin, Integer cottonPartMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonPartMin != null && entry.getKey().getCottonPart() < cottonPartMin) {
                continue;
            }
            if (cottonPartMax != null && entry.getKey().getCottonPart() > cottonPartMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }
    @Override
    public FileSystemResource exportData() {
        try {
            Path path = Files.createTempFile("export-", ".json");
        List<DtoSocks> socksList = new ArrayList<>();
        for (Map.Entry<Socks, Integer> entry : this.socksMap.entrySet()) {
            socksList.add(mapDto(entry.getKey(), entry.getValue()));
        }
        Files.write(path, objectMapper.writeValueAsBytes(socksList));
        return new FileSystemResource(path);
        } catch (IOException e) {
            throw new FileError("Ошибка при экспорте файла");
        }
    }
    private DtoSocks mapDto(Socks socks, int quantity) {
        DtoSocks dtoSocks = new DtoSocks();
        dtoSocks.setColor(socks.getColor());
        dtoSocks.setSize(socks.getSize());
        dtoSocks.setCottonPart(socks.getCottonPart());
        dtoSocks.setQuantity(quantity);
        return dtoSocks;
    }
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}