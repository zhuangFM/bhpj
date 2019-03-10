package com.netease.fimi.bhpj.service;

import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;

import java.util.List;

public interface TransactionRecordService {
    void addTransactionRecord(TransactionRecord transactionRecord);

    void modifyTransactionRecord(TransactionRecord transactionRecord);

    List<TransactionRecordInfo> getTransactionRecordInfoListByUserId(Integer userId);

    void deleteTransactionRecordById(Integer id);
}
