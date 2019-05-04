package com.netease.fimi.bhpj.service.impl;

import com.netease.fimi.bhpj.domain.Address;
import com.netease.fimi.bhpj.repository.AddressMapper;
import com.netease.fimi.bhpj.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void addAddress(Address address) {
        addressMapper.addAddress(address);
    }

    @Override
    public void modifyAddressById(Address address) {
        addressMapper.modifyAddressById(address);
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressMapper.getAddressById(id);
    }

    @Override
    public List<Address> getAddressListByUserId(Integer userId) {
        return addressMapper.getAddressListByUserId(userId);
    }

    @Override
    public void deleteAddressById(Integer id) {
        addressMapper.deleteAddressById(id);
    }
}
