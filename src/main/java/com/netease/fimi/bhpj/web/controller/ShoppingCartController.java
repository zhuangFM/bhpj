package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;
import com.netease.fimi.bhpj.service.ShoppingCartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    @ApiOperation(value = "根据用户userId 获取该用户的购物车", notes = "传入userId 返回该用户的购物车list")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @RequestMapping(value = "/get_shoppingCart_info_list_by_userId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShoppingCartInfoListByUserId(@RequestParam("userId") Integer userId) {
        Map<String, Object> json = Maps.newHashMap();
        List<ShoppingCartInfo> shoppingCartInfoList = shoppingCartService.getShoppingCartInfoListByUserId(userId);
        json.put("shoppingCartInfoList", shoppingCartInfoList);
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = "新增或者修改shoppingCart", notes = "此接口可能用不上！！")
    @ResponseBody
    @RequestMapping(value = "/save_shoppingCart", method = RequestMethod.POST)
    public Map<String, Object> saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        Map<String, Object> json = Maps.newHashMap();
        if (null == shoppingCart.getId()) {
            shoppingCartService.addShoppingCart(shoppingCart);
            log.info("add a shoppingCart {}", shoppingCart);
            json.put("msg", "add a shoppingCart");
        } else {
            shoppingCartService.modifyShoppingCart(shoppingCart);
            log.info("modify a shoppingCart {}", shoppingCart);
            json.put("msg", String.format("modify a shoppingCart where id is %d", shoppingCart.getId()));
        }
        return json;
    }

    @ApiOperation(value = "根据userId 删除用户购物车", notes = "此接口在 用户下单付款后清空购物车时使用")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "delete_shoppingCart_by_userId", method = RequestMethod.GET)
    public Map<String, Object> deleteShoppingCartByUserId(@RequestParam("userId") Integer userId) {
        Map<String, Object> json = Maps.newHashMap();
        shoppingCartService.deleteShoppingCartByUserId(userId);
        log.info("delete shoppingCarts where userId is {}", userId);
        json.put("msg", String.format("delete shoppingCarts where userId is {}", userId));
        return json;
    }
}
