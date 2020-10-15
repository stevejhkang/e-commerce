package com.example.shop.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class FileUtils {
    private static final String filePath = "C:\\ShopMVC\\src\\main\\webapp\\resources\\img\\";

    public static String uploadToLocalStorage(MultipartFile multipartFile) {

        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        originalFileName = multipartFile.getOriginalFilename();
        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        storedFileName = CommonUtils.getRandomString()+originalFileExtension;

        File file = new File(filePath + storedFileName);
        try {
            multipartFile.transferTo(file);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return storedFileName;
    }
    public static String deleteFile(String fileName){
        File file = new File(filePath+fileName);
        if(file.exists()){
            if(file.delete()) {
                return "deleted";
            } else {
                return "not deleted";
            }
        }
        return "";
    }
}
