package com.skypro.course_work_3.controller;

import com.skypro.course_work_3.dto.DtoSocks;
import com.skypro.course_work_3.model.Color;
import com.skypro.course_work_3.model.Size;
import com.skypro.course_work_3.service.impl.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    @Operation(summary = "Добавление товара (носки)")
    @Parameters(value = {
            @Parameter(name = "Цвет", example = "Черный"),
            @Parameter(name = "Размер", example = "S"),
            @Parameter(name = "Процентное содержание хлопка в составе носков", example = "70"),
            @Parameter(name = "Количество пар носков", example = "5"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось добавить приход."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны."),
    })
    public void addSocks(@RequestBody DtoSocks dtoSocks) {
        socksService.addSocks(dtoSocks);
    }

    @PutMapping
    @Operation(summary = "Отпуск товара (носки) со склада")
    @Parameters(value = {
            @Parameter(name = "Цвет", example = "BLACK, BROWN, BLUE, GREEN"),
            @Parameter(name = "Размер", example = "S, M, L"),
            @Parameter(name = "Процентное содержание хлопка в составе носков", example = "70"),
            @Parameter(name = "Количество пар носков", example = "5"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось произвести отпуск носков со склада."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат."),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны."),
    })
    public void outSocks(@RequestBody DtoSocks dtoSocks) {
        socksService.reduceQuantitySocks(dtoSocks);
    }

    @GetMapping
    @Operation(summary = "Возврат общего количества носков на складе.")
    @Parameters(value = {
            @Parameter(name = "Цвет", example = "BLACK, BROWN, BLUE, GREEN"),
            @Parameter(name = "Размер", example = "S, M, L"),
            @Parameter(name = "Минимальное значение хлопка в товаре", example = "1"),
            @Parameter(name = "Максимальное значение хлопка в товаре", example = "100"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны."),
    })
    public int getCounterSocks(@RequestParam(required = false, name = "Color") Color color,
                               @RequestParam(required = false, name = "Size") Size size,
                               @RequestParam(required = false, name = "CottonPartMin") Integer cottonPartMin,
                               @RequestParam(required = false, name = "CottonPartMax") Integer cottonPartMax) {
        return socksService.getQuantity(color, size, cottonPartMin, cottonPartMax);
    }

    @DeleteMapping
    @Operation(summary = "Списание испорченных (бракованных) носков")
    @Parameters(value = {
            @Parameter(name = "Цвет", example = "BLACK, BROWN, BLUE, GREEN"),
            @Parameter(name = "Размер", example = "S, M, L"),
            @Parameter(name = "Процентное содержание хлопка в составе носков", example = "70"),
            @Parameter(name = "Количество пар носков", example = "5"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен, товар списан со склада."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны."),
    })
    private void deleteDefectiveSocks(@RequestBody DtoSocks dtoSocks) {
        socksService.reduceQuantitySocks(dtoSocks);
    }
}
