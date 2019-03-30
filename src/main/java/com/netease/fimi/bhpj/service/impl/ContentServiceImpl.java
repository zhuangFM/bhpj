package com.netease.fimi.bhpj.service.impl;

import com.netease.fimi.bhpj.domain.Content;
import com.netease.fimi.bhpj.repository.ContentMapper;
import com.netease.fimi.bhpj.service.ContentService;
import com.netease.fimi.bhpj.util.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contentService")
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void addContent(Content content) {
        content.setCreateTime(TimeGetter.getCurrentTimeStr());
        content.setSelled(0);
        contentMapper.addContent(content);
    }

    @Override
    public void modifyContentById(Content content) {
        contentMapper.modifyContentById(content);
    }

    @Override
    public Content getContentById(Integer id) {
        return contentMapper.getContentById(id);
    }

    @Override
    public List<Content> getAllContentList() {
        return contentMapper.getAllContentList();
    }

    @Override
    public void deleteContentById(Integer id) {
        contentMapper.deleteContentById(id);
    }
}
