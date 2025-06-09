package com.example.dianping.service;

import com.example.dianping.common.CommonResponse;
import com.example.dianping.model.StoreComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreCommentService {
    CommonResponse createComment(StoreComment comment, MultipartFile[] images);
    List<StoreComment> listByStoreId(Integer storeId);
} 