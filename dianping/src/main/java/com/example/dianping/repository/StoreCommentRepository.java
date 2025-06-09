package com.example.dianping.repository;

import com.example.dianping.model.StoreComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreCommentRepository extends JpaRepository<StoreComment, Integer> {
    List<StoreComment> findByStoreIdOrderByCreateTimeDesc(Integer storeId);
} 