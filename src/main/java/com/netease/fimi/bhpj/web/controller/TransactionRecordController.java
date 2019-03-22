package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;
import com.netease.fimi.bhpj.service.TransactionRecordService;
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
@RequestMapping("/transactionRecord")
public class TransactionRecordController {
    @Autowired
    private TransactionRecordService transactionRecordService;
    private static Logger log = LoggerFactory.getLogger(TransactionRecordController.class);

    @ApiOperation(value = "根据userId获取改用户的交易记录", notes = "传入userId 后台返回该用户的交易记录list")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "/get_transactionRecord_info_list_by_userId", method = RequestMethod.GET)
    public Map<String, Object> getTransactionRecordInfoListByUserId(@RequestParam("userId") Integer userId) {
        List<TransactionRecordInfo> transactionRecordInfoList = transactionRecordService.getTransactionRecordInfoListByUserId(userId);
        Map<String, Object> json = Maps.newHashMap();
        json.put("transactionRecordInfoList", transactionRecordInfoList);
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = "批量新增或者修改content", notes = "此接口用在用户下单付款后 添加交易记录")
    @ApiImplicitParam(name = "transactionRecordList", value = "TransactionRecord数组", required = true, allowMultiple = true, dataType = "TransactionRecord")
    @ResponseBody
    @RequestMapping(value = "/save_transactionRecord", method = RequestMethod.POST)
    public Map<String, Object> saveTransactionRecord(@RequestBody List<TransactionRecord> transactionRecordList) {
        Map<String, Object> json = Maps.newHashMap();
        for (TransactionRecord item : transactionRecordList) {
            if (null == item.getId()) {
                transactionRecordService.addTransactionRecord(item);
                log.info("add a transactionRecord {}", item);
            } else {
                transactionRecordService.modifyTransactionRecord(item);
                log.info("modify a transactionRecord {}", item);
            }
        }
        json.put("msg", String.format("add or modify %d transactionRecord", transactionRecordList.size()));
        json.put("code", 1);
        return json;
    }
}
