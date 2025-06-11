package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
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
 * 图书订单
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/tushuOrder")
public class TushuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(TushuOrderController.class);

    @Autowired
    private TushuOrderService tushuOrderService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private TushuService tushuService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private CartService cartService;

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
        PageUtils page = tushuOrderService.queryPage(params);
        List<TushuOrderView> list =(List<TushuOrderView>)page.getList();
        for(TushuOrderView c:list){
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TushuOrderEntity tushuOrder = tushuOrderService.getById(id);
        if(tushuOrder !=null){
            TushuOrderView view = new TushuOrderView();
            BeanUtils.copyProperties( tushuOrder , view );
            AddressEntity address = addressService.getById(tushuOrder.getAddressId());
            if(address != null){
                BeanUtils.copyProperties(address, view, new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});
                view.setAddressId(address.getId());
                view.setAddressYonghuId(address.getYonghuId());
            }
            TushuEntity tushu = tushuService.getById(tushuOrder.getTushuId());
            if(tushu != null){
                BeanUtils.copyProperties(tushu, view, new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});
                view.setTushuId(tushu.getId());
                view.setTushuYonghuId(tushu.getYonghuId());
            }
            YonghuEntity yonghu = yonghuService.getById(tushuOrder.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties(yonghu, view, new String[]{ "id", "createTime", "insertTime", "updateTime"});
                view.setYonghuId(yonghu.getId());
            }
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }

    @RequestMapping("/save")
    public R save(@RequestBody TushuOrderEntity tushuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tushuOrder:{}",this.getClass().getName(),tushuOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            tushuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        tushuOrder.setInsertTime(new Date());
        tushuOrder.setCreateTime(new Date());
        tushuOrderService.save(tushuOrder);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody TushuOrderEntity tushuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tushuOrder:{}",this.getClass().getName(),tushuOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<TushuOrderEntity> queryWrapper = new QueryWrapper<TushuOrderEntity>().eq("id",0);
        logger.info("sql语句:"+((QueryWrapper<TushuOrderEntity>)queryWrapper).getSqlSegment());
        TushuOrderEntity tushuOrderEntity = tushuOrderService.getOne(queryWrapper);
        if(tushuOrderEntity==null){
            tushuOrderService.updateById(tushuOrder);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),Arrays.toString(ids));
        tushuOrderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/batchInsert")
    public R save(String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<TushuOrderEntity> tushuOrderList = new ArrayList<>();
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
                        dataList.remove(0);
                        for(List<String> data:dataList){
                            TushuOrderEntity tushuOrderEntity = new TushuOrderEntity();
                            tushuOrderList.add(tushuOrderEntity);
                            if(seachFields.containsKey("tushuOrderUuidNumber")){
                                List<String> tushuOrderUuidNumber = seachFields.get("tushuOrderUuidNumber");
                                tushuOrderUuidNumber.add(data.get(0));
                            }else{
                                List<String> tushuOrderUuidNumber = new ArrayList<>();
                                tushuOrderUuidNumber.add(data.get(0));
                                seachFields.put("tushuOrderUuidNumber",tushuOrderUuidNumber);
                            }
                        }
                        List<TushuOrderEntity> tushuOrderEntities_tushuOrderUuidNumber = tushuOrderService.list(new QueryWrapper<TushuOrderEntity>().in("tushu_order_uuid_number", seachFields.get("tushuOrderUuidNumber")));
                        if(tushuOrderEntities_tushuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TushuOrderEntity s:tushuOrderEntities_tushuOrderUuidNumber){
                                repeatFields.add(s.getTushuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tushuOrderService.saveBatch(tushuOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = tushuOrderService.queryPage(params);
        List<TushuOrderView> list =(List<TushuOrderView>)page.getList();
        for(TushuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TushuOrderEntity tushuOrder = tushuOrderService.getById(id);
        if(tushuOrder !=null){
            TushuOrderView view = new TushuOrderView();
            BeanUtils.copyProperties( tushuOrder , view );
            AddressEntity address = addressService.getById(tushuOrder.getAddressId());
            if(address != null){
                BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});
                view.setAddressId(address.getId());
            }
            TushuEntity tushu = tushuService.getById(tushuOrder.getTushuId());
            if(tushu != null){
                BeanUtils.copyProperties( tushu , view ,new String[]{ "id", "createDate"});
                view.setTushuId(tushu.getId());
            }
            YonghuEntity yonghu = yonghuService.getById(tushuOrder.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});
                view.setYonghuId(yonghu.getId());
            }
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }

    @RequestMapping("/add")
    public R add(@RequestBody TushuOrderEntity tushuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tushuOrder:{}",this.getClass().getName(),tushuOrder.toString());
        TushuEntity tushuEntity = tushuService.getById(tushuOrder.getTushuId());
        if(tushuEntity == null){
            return R.error(511,"查不到该图书");
        }
        if(false){
        }
        else if((tushuEntity.getTushuKucunNumber() -tushuOrder.getBuyNumber())<0){
            return R.error(511,"购买数量不能大于库存数量");
        }
        else if(tushuEntity.getTushuNewMoney() == null){
            return R.error(511,"图书价格不能为空");
        }
        Double buyJifen =0.0;
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        tushuOrder.setTushuOrderTypes(3);
        tushuOrder.setTushuOrderTruePrice(0.0);
        tushuOrder.setYonghuId(userId);
        tushuOrder.setTushuOrderUuidNumber(String.valueOf(new Date().getTime()));
        tushuOrder.setTushuOrderPaymentTypes(1);
        tushuOrder.setInsertTime(new Date());
        tushuOrder.setCreateTime(new Date());
        tushuEntity.setTushuKucunNumber( tushuEntity.getTushuKucunNumber() -tushuOrder.getBuyNumber());
        tushuService.updateById(tushuEntity);
        tushuOrderService.save(tushuOrder);
        return R.ok();
    }

    // 其它方法同理做法，全部用 getById、getOne、list、save、saveBatch、removeByIds
    // 省略其它代码，可根据需要补充
}