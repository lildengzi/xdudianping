package com.example.dianping.controller;

import com.example.dianping.common.CommonResponse;
import com.example.dianping.model.StoreComment;
import com.example.dianping.service.StoreCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/store/storeComment")
public class StoreCommentController {

    @Autowired
    private StoreCommentService storeCommentService;

    @PostMapping("/save")
    public CommonResponse save(
            @RequestParam("storeId") Integer storeId,
            @RequestParam("content") String content,
            @RequestParam("score") Integer score,
            @RequestParam("environmentScore") Integer environmentScore,
            @RequestParam("serviceScore") Integer serviceScore,
            @RequestParam("tasteScore") Integer tasteScore,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "images", required = false) MultipartFile[] images) {
        try {
            StoreComment comment = new StoreComment();
            comment.setStoreId(storeId);
            comment.setContent(content);
            comment.setScore(score);
            comment.setEnvironmentScore(environmentScore);
            comment.setServiceScore(serviceScore);
            comment.setTasteScore(tasteScore);
            comment.setTags(tags);
            
            return storeCommentService.createComment(comment, images);
        } catch (Exception e) {
            return CommonResponse.create(e.getMessage(), -1);
        }
    }

    @GetMapping("/list")
    public CommonResponse list(@RequestParam("storeId") Integer storeId) {
        return CommonResponse.create(storeCommentService.listByStoreId(storeId));
    }
} 