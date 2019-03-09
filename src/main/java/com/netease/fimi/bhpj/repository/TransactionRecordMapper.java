package com.netease.fimi.bhpj.repository;

import com.netease.fimi.bhpj.domain.TransactionRecord;
import com.netease.fimi.bhpj.domain.TransactionRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionRecordMapper {
    void addTransactionRecord(TransactionRecord transactionRecord);

    void modifyTransactionRecord(TransactionRecord transactionRecord);

    List<TransactionRecord> getTransactionRecordListByUserId(@Param("userId") Integer userId);

    List<TransactionRecordInfo> getTransactionRecordInfoListByUserId(@Param("userId") Integer userId);

    void deleteTransactionRecordById(@Param("id") Integer id);

}
