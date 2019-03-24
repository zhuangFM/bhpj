package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.Content;
import com.netease.fimi.bhpj.service.ContentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Value("${image.upload.basePath}")
    private String basePath;

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

    @ResponseBody
    @RequestMapping(value = "/upload_content_images",method = RequestMethod.POST)
    @ApiOperation(value = "上传content 本地图片", notes = "传入content的id 和 file")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "需要上传的图片 file", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(name = "id", value = "内容content的主键id", required = true)
    })

    public Map<String, Object> uploadContentImages(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Content content = contentService.getContentById(id);
        log.info("access api /upload_foodstuff_images where id is {}", id);

        String folderPath1 = this.basePath + id;
        File folder1 = new File(folderPath1);
        if (!folder1.exists()) {
            folder1.mkdir();
        }

        String path1 = folderPath1 + "/" + file.getOriginalFilename();
        FileOutputStream out = new FileOutputStream(path1);
        out.write(file.getBytes());
        out.flush();
        out.close();

        //update content
        content.setImagePath(file.getOriginalFilename());
        content.setImageType(1);
        contentService.modifyContentById(content);
        log.info("uploaded a image where path is {}", path1);

        Map<String, Object> json = Maps.newHashMap();
        json.put("msg", "successfully! upload content image where id = " + id);
        return json;
    }
}
