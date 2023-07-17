package com.wzg.creat.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class UpLoadController {


    
    /** 
     * @description: 上传文件
     * @author wangzg
     * @date: 2022/7/23 2:10
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void upLoadFile(MultipartFile upLoadFile, @RequestParam String path){
        log.info("param path is : " + path);
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }

            if (upLoadFile == null) {
                System.out.println("未选择文件");
                return;
            }

            String fileName = upLoadFile.getOriginalFilename();
            File saveFile = new File(path + "/" + fileName);
//            inputStream = upLoadFile.getInputStream();
//            fileOutputStream = new FileOutputStream(file + "/" + fileName);
//            byte[] bytArr = new byte[1024 * 1024 * 10];
//            int len;
//            while ((len = inputStream.read(bytArr)) != -1){
//                fileOutputStream.write(bytArr, 0, len);
//            }
            upLoadFile.transferTo(saveFile);
            log.info("upload success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

    }



}
