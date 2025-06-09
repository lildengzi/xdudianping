package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Note;
import com.example.sens.mapper.NoteMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.NoteService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
@Slf4j
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

}