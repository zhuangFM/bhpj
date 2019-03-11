package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;
import com.netease.fimi.bhpj.service.TransactionRecordService;
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
@RequestMapping("/transactionRecord")
public class TransactionRecordController {
    @Autowired
    private TransactionRecordService transactionRecordService;
    private static Logger log = LoggerFactory.getLogger(TransactionRecordController.class);

    @ResponseBody
    @RequestMapping("/get_transactionRecord_info_list_by_userId")
    public Map<String, Object> getTransactionRecordInfoListByUserId(@RequestParam("userId") Integer userId) {
        List<TransactionRecordInfo> transactionRecordInfoList = transactionRecordService.getTransactionRecordInfoListByUserId(userId);
        Map<String, Object> json = Maps.newHashMap();
        json.put("transactionRecordInfoList", transactionRecordInfoList);
        json.put("code", 1);
        return json;
    }

    @ResponseBody
    @RequestMapping("/save_transactionRecord")
    public Map<String, Object> saveTransactionRecord(@RequestBody List<TransactionRecord> transactionRecordList) {
        Map<String, Object> json = Maps.newHashMap();
        for (TransactionRecord item : transactionRecordList) {
            if (null == item.getId()) {
                transactionRecordService.addTransactionRecord(item);
                log.info("add a transactionRecord {}", item);
            }
            else{
                transactionRecordService.modifyTransactionRecord(item);
                log.info("modify a transactionRecord {}", item);
            }
        }
        json.put("msg",String.format("add or modify %d transactionRecord",transactionRecordList.size()));
        json.put("code",1);
        return json;
    }
}
