package com.example.sens.controller.admin;

import com.example.sens.common.constant.CommonConstant;
import com.example.sens.controller.common.BaseController;
import com.liuyanzhao.yztool.common.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 *     后台附件控制器
 * </pre>
 *
 * @author : saysky
 * @date 2021/4/17
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/file")
public class AttachmentController extends BaseController {


    /**
     * 上传文件
     *
     * @param file file
     * @return Map
     */
    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>(1);
        Map<String, String> uploadMap = FileUtil.upload(file, CommonConstant.UPLOADS_PATH);
        map.put("link", uploadMap.get("filePath"));
        map.put("location", uploadMap.get("filePath"));
        String originFileName = uploadMap.get("originFileName");
        map.put("originFileName", originFileName);
        map.put("fileName", originFileName.substring(0, file.getOriginalFilename().lastIndexOf('.')));

        return map;
    }


}
