package com.wzg.creat.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Struct;

@Slf4j
@RestController
public class DownLoadController {

    /**
     * @description:
     * @param:
     * @return:
     * @author wangzg
     * @date: 2021/3/5 17:57
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(@RequestParam String filePath, @RequestParam String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            FileInputStream inputStream = null;
            BufferedInputStream bufferIn = null;
            ServletOutputStream os = null;
            byte[] bytes = new byte[1024];
            int a;
            try {
                inputStream = new FileInputStream(file);
                bufferIn = new BufferedInputStream(inputStream);
                os = response.getOutputStream();
                while ((a = bufferIn.read(bytes)) != -1) {
                    os.write(bytes, 0, a);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                os.close();
                bufferIn.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    /** 
     * @description: 下载网络文件
     * @param:  
     * @return:  
     * @author wangzg
     * @date: 2022/7/23 2:12
     */
    @RequestMapping(value = "/netfile", method = RequestMethod.GET)
    @ResponseBody
    public void fileFromNet(@RequestParam("url") String urlStr, @RequestParam String savePath, HttpServletResponse response) throws IOException {

        if (StrUtil.isBlank(savePath) || StrUtil.isBlank(urlStr)) {
            log.info("param savePath or url can't be null");
        }
        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = connection.getInputStream();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//
//            FileOutputStream outputStream = new FileOutputStream(savePath + "/test.txt");
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//            byte[] bytes = new byte[1024 * 1024 * 1];
//            int len;
//            while ((len = (bufferedInputStream.read(bytes))) != -1) {
//                bufferedOutputStream.write(bytes, 0, len);
//            }
//            bufferedOutputStream.close();
//            outputStream.close();
//            bufferedInputStream.close();
//            inputStream.close();


            String fileName = urlStr.substring(urlStr.lastIndexOf("/"));
            long size = HttpUtil.downloadFile(urlStr, FileUtil.file(savePath + "/" + (StrUtil.isNotBlank(fileName) ? fileName : "test.txt")));
            log.info("download file size:" + size);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }


}
