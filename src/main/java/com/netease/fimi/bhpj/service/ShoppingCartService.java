package com.netease.fimi.bhpj.service;

import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;

import java.util.List;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCart shoppingCart);

    void modifyShoppingCart(ShoppingCart shoppingCart);

    List<ShoppingCartInfo> getShoppingCartInfoListByUserId(Integer userId);

    void deleteShoppingCartByUserId(Integer userId);

    void deleteShoppingCartById(Integer id);
}
