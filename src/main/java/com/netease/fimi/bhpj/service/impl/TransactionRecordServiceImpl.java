package com.netease.fimi.bhpj.service.impl;


import com.netease.fimi.bhpj.domain.Content;
import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;
import com.netease.fimi.bhpj.repository.ContentMapper;
import com.netease.fimi.bhpj.repository.TransactionRecordMapper;
import com.netease.fimi.bhpj.service.TransactionRecordService;
import com.netease.fimi.bhpj.util.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transactionRecordService")
public class TransactionRecordServiceImpl implements TransactionRecordService {
    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void addTransactionRecord(TransactionRecord transactionRecord) {
        //改库存
        Content content = contentMapper.getContentById(transactionRecord.getContentId());
        content.setContain(content.getContain()-transactionRecord.getAmount());
        contentMapper.modifyContentById(content);
        //create_time
        transactionRecord.setCreateTime(TimeGetter.getCurrentTimeStr());
        transactionRecordMapper.addTransactionRecord(transactionRecord);
    }

    @Override
    public void modifyTransactionRecord(TransactionRecord transactionRecord) {
        transactionRecordMapper.modifyTransactionRecord(transactionRecord);
    }

    @Override
    public List<TransactionRecordInfo> getTransactionRecordInfoListByUserId(Integer userId) {
        return transactionRecordMapper.getTransactionRecordInfoListByUserId(userId);
    }

    @Override
    public void deleteTransactionRecordById(Integer id) {
        transactionRecordMapper.deleteTransactionRecordById(id);
    }
}
