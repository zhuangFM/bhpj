package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.Content;
import com.netease.fimi.bhpj.service.ContentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    private static Logger log = LoggerFactory.getLogger(ContentController.class);

    @ApiOperation(value = "获取所有的content", notes = "获取所有的content")
    @ResponseBody
    @RequestMapping(value = "/get_all_content_list", method = RequestMethod.GET)
    public Map<String, Object> getAllContentList() {
        Map<String, Object> json = Maps.newHashMap();
        List<Content> contentList = contentService.getAllContentList();
        json.put("contentList", contentList);
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = "新增或者修改content", notes = "传入content 后台根据id是否为null判断是新增还是修改")
    @ApiImplicitParam(name = "content", value = "内容详细实体content", required = true, dataType = "Content")
    @ResponseBody
    @RequestMapping(value = "/save_content", method = RequestMethod.POST)
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

    @ApiOperation(value = "删除content", notes = "传入content的id 根据id删除content")
    @ApiImplicitParam(name = "id", value = "内容content的主键id", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "/delete_content_by_id", method = RequestMethod.GET)
    public Map<String, Object> deleteContentById(@RequestParam("id") Integer id) {
        Map<String, Object> json = Maps.newHashMap();
        log.info("delete a content where id is {}", id);
        json.put("msg", String.format("delete a content where id is %d", id));
        json.put("code", 1);
        contentService.deleteContentById(id);
        return json;
    }
}
