package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 收货地址
 * 后端接口
 * @author
 * @email
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = addressService.queryPage(params);

        //字典表数据转换
        List<AddressView> list =(List<AddressView>)page.getList();
        for(AddressView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        AddressEntity address = addressService.getById(id);
        if(address !=null){
            //entity转view
            AddressView view = new AddressView();
            BeanUtils.copyProperties(address, view);

            //级联表
            YonghuEntity yonghu = yonghuService.getById(address.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties(yonghu, view, new String[]{ "id", "createTime", "insertTime", "updateTime"});
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AddressEntity address, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,address:{}",this.getClass().getName(),address.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            address.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<AddressEntity> queryWrapper = new QueryWrapper<AddressEntity>()
                .eq("yonghu_id", address.getYonghuId())
                .eq("address_name", address.getAddressName())
                .eq("address_phone", address.getAddressPhone())
                .eq("address_dizhi", address.getAddressDizhi())
                .eq("isdefault_types", address.getIsdefaultTypes());

        logger.info("sql语句:"+((QueryWrapper)queryWrapper).getSqlSegment());
        AddressEntity addressEntity = addressService.getOne(queryWrapper, false);
        if(addressEntity==null){
            address.setInsertTime(new Date());
            address.setCreateTime(new Date());
            Integer isdefaultTypes = address.getIsdefaultTypes();
            if(isdefaultTypes == 2 ){//如果当前的是默认地址，把当前用户的其他改为不是默认地址
                List<AddressEntity> addressEntitys = addressService.list(new QueryWrapper<AddressEntity>().eq("isdefault_types",2));
                if(addressEntitys != null && addressEntitys.size()>0){
                    for(AddressEntity a:addressEntitys)
                        a.setIsdefaultTypes(1);
                    addressService.updateBatchById(addressEntitys);
                }
            }
            addressService.save(address);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AddressEntity address, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,address:{}",this.getClass().getName(),address.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        //if(false)
        //    return R.error(511,"永远不会进入");
        //else if("用户".equals(role))
        //    address.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<AddressEntity> queryWrapper = new QueryWrapper<AddressEntity>()
                .notIn("id",address.getId())
                .eq("yonghu_id", address.getYonghuId())
                .eq("address_name", address.getAddressName())
                .eq("address_phone", address.getAddressPhone())
                .eq("address_dizhi", address.getAddressDizhi())
                .eq("isdefault_types", address.getIsdefaultTypes());

        logger.info("sql语句:"+((QueryWrapper)queryWrapper).getSqlSegment());
        AddressEntity addressEntity = addressService.getOne(queryWrapper, false);
        address.setUpdateTime(new Date());
        if(addressEntity==null){
            Integer isdefaultTypes = address.getIsdefaultTypes();
            if(isdefaultTypes == 2 ){//如果当前的是默认地址，把当前用户的其他改为不是默认地址
                List<AddressEntity> addressEntitys = addressService.list(new QueryWrapper<AddressEntity>().eq("isdefault_types",2));
                if(addressEntitys != null && addressEntitys.size()>0){
                    for(AddressEntity a:addressEntitys)
                        a.setIsdefaultTypes(1);
                    addressService.updateBatchById(addressEntitys);
                }
            }
            addressService.updateById(address);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),Arrays.toString(ids));
        addressService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<AddressEntity> addressList = new ArrayList<>();
            Map<String, List<String>> seachFields= new HashMap<>();
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            AddressEntity addressEntity = new AddressEntity();
                            // TODO: 补全数据填充逻辑
                            addressList.add(addressEntity);

                            //把要查询是否重复的字段放入map中
                            if(seachFields.containsKey("addressPhone")){
                                List<String> addressPhone = seachFields.get("addressPhone");
                                addressPhone.add(data.get(0));//要改的
                            }else{
                                List<String> addressPhone = new ArrayList<>();
                                addressPhone.add(data.get(0));//要改的
                                seachFields.put("addressPhone",addressPhone);
                            }
                        }

                        //查询是否重复
                        List<AddressEntity> addressEntities_addressPhone = addressService.list(new QueryWrapper<AddressEntity>().in("address_phone", seachFields.get("addressPhone")));
                        if(addressEntities_addressPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(AddressEntity s:addressEntities_addressPhone){
                                repeatFields.add(s.getAddressPhone());
                            }
                            return R.error(511,"数据库的该表中的 [电话] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        addressService.saveBatch(addressList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = addressService.queryPage(params);

        //字典表数据转换
        List<AddressView> list =(List<AddressView>)page.getList();
        for(AddressView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        AddressEntity address = addressService.getById(id);
        if(address !=null){
            AddressView view = new AddressView();
            BeanUtils.copyProperties(address, view);

            //级联表
            YonghuEntity yonghu = yonghuService.getById(address.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties(yonghu, view, new String[]{ "id", "createDate"});
                view.setYonghuId(yonghu.getId());
            }
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody AddressEntity address, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,address:{}",this.getClass().getName(),address.toString());
        Wrapper<AddressEntity> queryWrapper = new QueryWrapper<AddressEntity>()
                .eq("yonghu_id", address.getYonghuId())
                .eq("address_name", address.getAddressName())
                .eq("address_phone", address.getAddressPhone())
                .eq("address_dizhi", address.getAddressDizhi())
                .eq("isdefault_types", address.getIsdefaultTypes());
        logger.info("sql语句:"+((QueryWrapper)queryWrapper).getSqlSegment());
        AddressEntity addressEntity = addressService.getOne(queryWrapper, false);
        if(addressEntity==null){
            address.setInsertTime(new Date());
            address.setCreateTime(new Date());
            Integer isdefaultTypes = address.getIsdefaultTypes();
            if(isdefaultTypes == 2 ){
                List<AddressEntity> addressEntitys = addressService.list(new QueryWrapper<AddressEntity>().eq("isdefault_types",2));
                if(addressEntitys != null && addressEntitys.size()>0){
                    for(AddressEntity a:addressEntitys)
                        a.setIsdefaultTypes(1);
                    addressService.updateBatchById(addressEntitys);
                }
            }
            addressService.save(address);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
}