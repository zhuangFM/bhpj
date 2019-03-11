package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;
import com.netease.fimi.bhpj.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

    @RequestMapping("/get_shoppingCart_info_list_by_userId")
    @ResponseBody
    public Map<String, Object> getShoppingCartInfoListByUserId(@RequestParam("userId") Integer userId) {
        Map<String, Object> json = Maps.newHashMap();
        List<ShoppingCartInfo> shoppingCartInfoList = shoppingCartService.getShoppingCartInfoListByUserId(userId);
        json.put("shoppingCartInfoList", shoppingCartInfoList);
        json.put("code", 1);
        return json;
    }

    @ResponseBody
    @RequestMapping("/save_shoppingCart")
    public Map<String,Object> saveShoppingCart(@RequestBody ShoppingCart shoppingCart){
        Map<String,Object> json = Maps.newHashMap();
        if(null == shoppingCart.getId()){
            shoppingCartService.addShoppingCart(shoppingCart);
            log.info("add a shoppingCart {}",shoppingCart);
            json.put("msg","add a shoppingCart");
        }
        else{
            shoppingCartService.modifyShoppingCart(shoppingCart);
            log.info("modify a shoppingCart {}",shoppingCart);
            json.put("msg",String.format("modify a shoppingCart where id is %d",shoppingCart.getId()));
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("delete_shoppingCart_by_userId")
    public Map<String,Object> deleteShoppingCartByUserId(@RequestParam("userId") Integer userId){
        Map<String,Object> json = Maps.newHashMap();
        shoppingCartService.deleteShoppingCartByUserId(userId);
        log.info("delete shoppingCarts where userId is {}",userId);
        json.put("msg",String.format("delete shoppingCarts where userId is {}",userId));
        return json;
    }
}
