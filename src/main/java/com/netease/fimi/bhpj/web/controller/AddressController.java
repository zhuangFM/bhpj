package com.netease.fimi.bhpj.web.controller;

import com.google.common.collect.Maps;
import com.netease.fimi.bhpj.domain.Address;
import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.service.AddressService;
import com.netease.fimi.bhpj.util.ConstField;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "新增或修改address", notes = "传入address对象 后台根据是否有id判断是添加还是修改")
    @ApiImplicitParam(name = "address", value = "address对象", required = true, dataType = "Address")
    @ResponseBody
    @RequestMapping(value = "/save_address", method = RequestMethod.POST)
    public Map<String, Object> addAddress(@RequestBody Address address, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        if(null==address.getId()){
            addressService.addAddress(address);
            json.put("msg","add a address");
            json.put("address",address);
        }
        else{
            addressService.modifyAddressById(address);
            json.put("msg",String.format("modify a address where id is %d",address.getId()));
            json.put("address",address);
        }
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = " 根据id获取单个address", notes = "根据id获取单个address")
    @ApiImplicitParam(name = "id", value = "address的主键id", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "/get_address_by_id", method = RequestMethod.GET)
    public Map<String, Object> getAddressById(@RequestParam("id") Integer id, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        Address address = addressService.getAddressById(id);
        json.put("address", address);
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = " 根据userId获取address list", notes = "根据userId获取address list")
    @ApiImplicitParam(name = "userId", value = "user的主键id", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "/get_address_list_by_user_id", method = RequestMethod.GET)
    public Map<String, Object> getAddressListByUserId(@RequestParam("userId") Integer userId, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        List<Address> addressList = addressService.getAddressListByUserId(userId);
        json.put("addressList", addressList);
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        json.put("code", 1);
        return json;
    }

    @ApiOperation(value = " 根据id删除单个address", notes = "根据id删除单个address")
    @ApiImplicitParam(name = "id", value = "address的主键id", required = true, dataType = "Integer")
    @ResponseBody
    @RequestMapping(value = "/delete_address_by_id", method = RequestMethod.GET)
    public Map<String, Object> deleteAddressById(@RequestParam("id") Integer id, HttpServletRequest request) {
        Map<String, Object> json = Maps.newHashMap();
        addressService.deleteAddressById(id);
        json.put("msg",String.format("delete a address where id is %d",id));
        json.put("user", (User) request.getSession().getAttribute(ConstField.USER));
        json.put("code", 1);
        return json;
    }


}
