package com.netease.fimi.bhpj.service.impl;

import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;
import com.netease.fimi.bhpj.repository.ShoppingCartMapper;
import com.netease.fimi.bhpj.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartMapper.addShoppingCart(shoppingCart);
    }

    @Override
    public void modifyShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartMapper.modifyShoppingCartById(shoppingCart);
    }

    @Override
    public List<ShoppingCartInfo> getShoppingCartInfoListByUserId(Integer userId) {
        return shoppingCartMapper.getShoppingCartInfoListByUserId(userId);
    }

    @Override
    public void deleteShoppingCartByUserId(Integer userId) {
        shoppingCartMapper.deleteShoppingCartByUserId(userId);
    }
}
