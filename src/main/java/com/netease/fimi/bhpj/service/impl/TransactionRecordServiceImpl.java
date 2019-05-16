package com.netease.fimi.bhpj.service.impl;


import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;
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

    @Override
    public void addTransactionRecord(TransactionRecord transactionRecord) {
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
