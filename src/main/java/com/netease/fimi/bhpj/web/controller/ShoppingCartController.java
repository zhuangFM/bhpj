package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.ShoppingCart;
import com.netease.fimi.bhpj.domain.ShoppingCartInfo;
import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.service.ShoppingCartService;
import com.netease.fimi.bhpj.util.ConstField;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> getShoppingCartInfoListByUserId(@RequestParam("userId") Integer userId, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        List<ShoppingCartInfo> shoppingCartInfoList = shoppingCartService.getShoppingCartInfoListByUserId(userId);
        json.put("shoppingCartInfoList", shoppingCartInfoList);
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = "新增或修改shoppingCart", notes = "传入shoppingCart对象 后台根据id是否为null 判断是新增还是修改")
    @ResponseBody
    @RequestMapping(value = "/save_shoppingCart", method = RequestMethod.POST)
    public Map<String, Object> saveShoppingCart(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request) {
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
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        return json;
    }

    @ApiOperation(value = "根据id 删除单条购物车记录", notes = "传入单条购物车记录的id")
    @ApiImplicitParam(name = "id", value = "单条购物车记录的id", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "delete_shoppingCart_by_id", method = RequestMethod.GET)
    public Map<String, Object> deleteShoppingCartById(@RequestParam("id") Integer id, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        shoppingCartService.deleteShoppingCartById(id);
        log.info("delete single shoppingCart record where id is {}", id);
        json.put("msg", String.format("delete one shoppingCart record  where id is {}", id));
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        return json;
    }

    @ApiOperation(value = "根据userId 删除用户购物车", notes = "此接口在 用户下单付款后清空购物车时使用")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "delete_shoppingCart_by_userId", method = RequestMethod.GET)
    public Map<String, Object> deleteShoppingCartByUserId(@RequestParam("userId") Integer userId, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        shoppingCartService.deleteShoppingCartByUserId(userId);
        log.info("delete shoppingCarts where userId is {}", userId);
        json.put("msg", String.format("delete shoppingCarts where userId is {}", userId));
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        return json;
    }
}
