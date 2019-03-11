package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.Content;
import com.netease.fimi.bhpj.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    private static Logger log = LoggerFactory.getLogger(ContentController.class);

    @ResponseBody
    @RequestMapping("/get_all_content_list")
    public Map<String, Object> getAllContentList() {
        Map<String, Object> json = Maps.newHashMap();
        List<Content> contentList = contentService.getAllContentList();
        json.put("contentList", contentList);
        json.put("code", 1);
        return json;
    }

    @ResponseBody
    @RequestMapping("/save_content")
    public Map<String, Object> saveContent(@RequestBody Content content) {
        Map<String, Object> json = Maps.newHashMap();
        if (null == content.getId()) {
            contentService.addContent(content);
            log.info("add a content {}", content);
            json.put("msg", "add a content");
        } else {
            contentService.modifyContentById(content);
            log.info("modify a content {}", content);
            json.put("msg", String.format("modify a content where id is %s", content.getId()));
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete_content_by_id")
    public Map<String, Object> deleteContentById(@RequestParam("id") Integer id) {
        Map<String, Object> json = Maps.newHashMap();
        log.info("delete a content where id is {}", id);
        json.put("msg", String.format("delete a content where id is %d", id));
        json.put("code", 1);
        contentService.deleteContentById(id);
        return json;
    }
}
