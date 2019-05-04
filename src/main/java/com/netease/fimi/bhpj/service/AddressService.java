package com.netease.fimi.bhpj.service;

import com.netease.fimi.bhpj.domain.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);

    void modifyAddressById(Address address);

    Address getAddressById(Integer id);

    List<Address> getAddressListByUserId(Integer userId);

    void deleteAddressById(Integer id);
}
