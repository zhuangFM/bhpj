package com.netease.fimi.bhpj.repository;

import com.netease.fimi.bhpj.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContentMapper {
    void addContent(Content content);

    void modifyContentById(Content content);

    Content getContentById(@Param("id") Integer id);

    List<Content> getAllContentList();

    void deleteContentById(@Param("id") Integer id);
}
