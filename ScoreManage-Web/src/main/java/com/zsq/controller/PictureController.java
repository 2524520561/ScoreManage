package com.zsq.controller;



import com.zsq.common.FastDFSClient;
import com.zsq.common.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("scoreManage")
public class PictureController {
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String imageUp(@RequestParam(value = "file", required = false) MultipartFile uploadFile){
        String originalFilename = uploadFile.getOriginalFilename();
        String extendName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client/fdfs_client.conf");
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extendName);
            String url = IMAGE_SERVER_URL+path;
            Map result = new HashMap();
            result.put("error",0);
            result.put("url",url);
            String resultJson = JsonUtils.objectToJson(result);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error",1);
            result.put("message","上传失败");
            String resultJson = JsonUtils.objectToJson(result);
            return resultJson;
        }
    }


}
