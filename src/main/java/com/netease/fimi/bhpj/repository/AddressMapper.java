package com.netease.fimi.bhpj.repository;

import com.netease.fimi.bhpj.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressMapper {
    void addAddress(Address address);

    void modifyAddressById(Address address);

    Address getAddressById(@Param("id") Integer id);

    List<Address> getAddressListByUserId(@Param("userId") Integer userId);

    void deleteAddressById(@Param("id") Integer id);



}
