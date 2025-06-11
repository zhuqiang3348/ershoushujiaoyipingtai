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
 * 图书
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/tushu")
public class TushuController {
    private static final Logger logger = LoggerFactory.getLogger(TushuController.class);

    @Autowired
    private TushuService tushuService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
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
        params.put("tushuDeleteStart",1);params.put("tushuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = tushuService.queryPage(params);

        List<TushuView> list =(List<TushuView>)page.getList();
        for(TushuView c:list){
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
        TushuEntity tushu = tushuService.getById(id);
        if(tushu !=null){
            TushuView view = new TushuView();
            BeanUtils.copyProperties(tushu, view);

            YonghuEntity yonghu = yonghuService.getById(tushu.getYonghuId());
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

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TushuEntity tushu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tushu:{}",this.getClass().getName(),tushu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            tushu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<TushuEntity> queryWrapper = new QueryWrapper<TushuEntity>()
                .eq("yonghu_id", tushu.getYonghuId())
                .eq("tushu_name", tushu.getTushuName())
                .eq("tushu_zuozhe", tushu.getTushuZuozhe())
                .eq("tushu_chubanshe", tushu.getTushuChubanshe())
                .eq("tushu_types", tushu.getTushuTypes())
                .eq("tushu_kucun_number", tushu.getTushuKucunNumber())
                .eq("tushu_clicknum", tushu.getTushuClicknum())
                .eq("shangxia_types", tushu.getShangxiaTypes())
                .eq("tushu_delete", tushu.getTushuDelete());

        logger.info("sql语句:"+queryWrapper);
        TushuEntity tushuEntity = tushuService.getOne(queryWrapper);
        if(tushuEntity==null){
            tushu.setTushuClicknum(1);
            tushu.setShangxiaTypes(1);
            tushu.setTushuDelete(1);
            tushu.setCreateTime(new Date());
            tushuService.save(tushu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TushuEntity tushu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tushu:{}",this.getClass().getName(),tushu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<TushuEntity> queryWrapper = new QueryWrapper<TushuEntity>()
                .ne("id",tushu.getId())
                .eq("yonghu_id", tushu.getYonghuId())
                .eq("tushu_name", tushu.getTushuName())
                .eq("tushu_zuozhe", tushu.getTushuZuozhe())
                .eq("tushu_chubanshe", tushu.getTushuChubanshe())
                .eq("tushu_types", tushu.getTushuTypes())
                .eq("tushu_kucun_number", tushu.getTushuKucunNumber())
                .eq("tushu_clicknum", tushu.getTushuClicknum())
                .eq("shangxia_types", tushu.getShangxiaTypes())
                .eq("tushu_delete", tushu.getTushuDelete());

        logger.info("sql语句:"+queryWrapper);
        TushuEntity tushuEntity = tushuService.getOne(queryWrapper);
        if("".equals(tushu.getTushuPhoto()) || "null".equals(tushu.getTushuPhoto())){
            tushu.setTushuPhoto(null);
        }
        if(tushuEntity==null){
            tushuService.updateById(tushu);
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
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(), Arrays.toString(ids));
        ArrayList<TushuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TushuEntity tushuEntity = new TushuEntity();
            tushuEntity.setId(id);
            tushuEntity.setTushuDelete(2);
            list.add(tushuEntity);
        }
        if(list != null && list.size() >0){
            tushuService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<TushuEntity> tushuList = new ArrayList<>();
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
                            TushuEntity tushuEntity = new TushuEntity();
                            // TODO: 填充字段
                            tushuList.add(tushuEntity);
                        }
                        tushuService.saveBatch(tushuList);
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
        PageUtils page = tushuService.queryPage(params);

        List<TushuView> list =(List<TushuView>)page.getList();
        for(TushuView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TushuEntity tushu = tushuService.getById(id);
        if(tushu !=null){

            tushu.setTushuClicknum(tushu.getTushuClicknum()+1);
            tushuService.updateById(tushu);

            TushuView view = new TushuView();
            BeanUtils.copyProperties(tushu, view);

            YonghuEntity yonghu = yonghuService.getById(tushu.getYonghuId());
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
    public R add(@RequestBody TushuEntity tushu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tushu:{}",this.getClass().getName(),tushu.toString());
        Wrapper<TushuEntity> queryWrapper = new QueryWrapper<TushuEntity>()
                .eq("yonghu_id", tushu.getYonghuId())
                .eq("tushu_name", tushu.getTushuName())
                .eq("tushu_zuozhe", tushu.getTushuZuozhe())
                .eq("tushu_chubanshe", tushu.getTushuChubanshe())
                .eq("tushu_types", tushu.getTushuTypes())
                .eq("tushu_kucun_number", tushu.getTushuKucunNumber())
                .eq("tushu_clicknum", tushu.getTushuClicknum())
                .eq("shangxia_types", tushu.getShangxiaTypes())
                .eq("tushu_delete", tushu.getTushuDelete());
        logger.info("sql语句:"+queryWrapper);
        TushuEntity tushuEntity = tushuService.getOne(queryWrapper);
        if(tushuEntity==null){
            tushu.setTushuDelete(1);
            tushu.setCreateTime(new Date());
            tushuService.save(tushu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
}