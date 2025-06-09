package com.example.dianping.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "store_comment")
public class StoreComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "environment_score", nullable = false)
    private Integer environmentScore;

    @Column(name = "service_score", nullable = false)
    private Integer serviceScore;

    @Column(name = "taste_score", nullable = false)
    private Integer tasteScore;

    @Column(length = 500)
    private String tags;

    @Column(length = 1000)
    private String images;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
} 