package com.carvia.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        logger.info("File name: " + name);
        //Ideally you shall read bytes using multipartFile.getInputStream() and store it appropriately
        byte[] bytes = multipartFile.getBytes();
        logger.info("File uploaded content: {}", new String(bytes));
        return "success";
    }
}
