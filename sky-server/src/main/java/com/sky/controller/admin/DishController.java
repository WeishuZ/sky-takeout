package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * dish management
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "dish mangement")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * add new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Add new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * dish pagination inquiry
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("dish pagination inquiry")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("dish pagination inquiry: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete dishes by id
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete dishes by id")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Delete dishes by ids: {}", ids);
        dishService.deleteBatch(ids);

        return null;
    }
}
