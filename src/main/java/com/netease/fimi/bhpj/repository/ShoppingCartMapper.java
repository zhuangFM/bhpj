package com.netease.fimi.bhpj.repository;

import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    void addShoppingCart(ShoppingCart shoppingCart);

    void modifyShoppingCartById(ShoppingCart shoppingCart);

    List<ShoppingCart> getShoppingCartListByUserId(@Param("userId") Integer userId);

    List<ShoppingCartInfo> getShoppingCartInfoListByUserId(@Param("userId") Integer userId);

    void deleteShoppingCartByUserId(@Param("userId") Integer userId);

}
