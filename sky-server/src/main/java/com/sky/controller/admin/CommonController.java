package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * common controller
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags = "common interface")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * upload file
     * @param file
     * @return
     */
    @ApiOperation("upload files")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("upload file");
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("upload file error", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}

