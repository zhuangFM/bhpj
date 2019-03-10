package com.netease.fimi.bhpj.service;

import com.netease.fimi.bhpj.domain.Content;

import java.util.List;

public interface ContentService {
    void addContent(Content content);

    void modifyContentById(Content content);

    Content getContentById(Integer id);

    List<Content> getAllContentList();

    void deleteContentById(Integer id);
}
