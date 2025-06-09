package com.example.dianping.service.impl;

import com.example.dianping.common.CommonResponse;
import com.example.dianping.model.StoreComment;
import com.example.dianping.repository.StoreCommentRepository;
import com.example.dianping.service.StoreCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StoreCommentServiceImpl implements StoreCommentService {

    @Autowired
    private StoreCommentRepository storeCommentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public CommonResponse createComment(StoreComment comment, MultipartFile[] images) {
        try {
            // 处理图片上传
            if (images != null && images.length > 0) {
                List<String> imageUrls = new ArrayList<>();
                for (MultipartFile image : images) {
                    if (!image.isEmpty()) {
                        String originalFilename = image.getOriginalFilename();
                        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        String newFilename = UUID.randomUUID().toString() + extension;
                        
                        // 确保上传目录存在
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }
                        
                        // 保存文件
                        File destFile = new File(uploadPath + File.separator + newFilename);
                        image.transferTo(destFile);
                        
                        // 添加到图片URL列表
                        imageUrls.add("/uploads/" + newFilename);
                    }
                }
                // 将图片URL保存到评论中
                comment.setImages(String.join(",", imageUrls));
            }

            // 保存评论
            storeCommentRepository.save(comment);
            return CommonResponse.create(comment);
        } catch (IOException e) {
            return CommonResponse.create("图片上传失败: " + e.getMessage(), -1);
        } catch (Exception e) {
            return CommonResponse.create("评论保存失败: " + e.getMessage(), -1);
        }
    }

    @Override
    public List<StoreComment> listByStoreId(Integer storeId) {
        return storeCommentRepository.findByStoreIdOrderByCreateTimeDesc(storeId);
    }
} 