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
 * 字典
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private YonghuService yonghuService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    @IgnoreAuth
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = dictionaryService.queryPage(params);

        List<DictionaryView> list =(List<DictionaryView>)page.getList();
        for(DictionaryView c:list){
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
        DictionaryEntity dictionary = dictionaryService.getById(id);
        if(dictionary !=null){
            DictionaryView view = new DictionaryView();
            BeanUtils.copyProperties(dictionary, view);

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
    public R save(@RequestBody DictionaryEntity dictionary, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<DictionaryEntity> queryWrapper = new QueryWrapper<DictionaryEntity>()
                .eq("dic_code", dictionary.getDicCode())
                .eq("index_name", dictionary.getIndexName());
        if(dictionary.getDicCode().contains("_erji_types")){
            queryWrapper = ((QueryWrapper<DictionaryEntity>) queryWrapper).eq("super_id",dictionary.getSuperId());
        }
        logger.info("sql语句:"+queryWrapper);
        DictionaryEntity dictionaryEntity = dictionaryService.getOne(queryWrapper);
        if(dictionaryEntity==null){
            dictionary.setCreateTime(new Date());
            dictionaryService.save(dictionary);
            //字典表新增数据,把数据再重新查出,放入监听器中
            List<DictionaryEntity> dictionaryEntities = dictionaryService.list(new QueryWrapper<DictionaryEntity>());
            ServletContext servletContext = request.getServletContext();
            Map<String, Map<Integer,String>> map = new HashMap<>();
            for(DictionaryEntity d :dictionaryEntities){
                Map<Integer, String> m = map.get(d.getDicCode());
                if(m ==null || m.isEmpty()){
                    m = new HashMap<>();
                }
                m.put(d.getCodeIndex(),d.getIndexName());
                map.put(d.getDicCode(),m);
            }
            servletContext.setAttribute("dictionaryMap",map);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DictionaryEntity dictionary, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<DictionaryEntity> queryWrapper = new QueryWrapper<DictionaryEntity>()
                .ne("id",dictionary.getId())
                .eq("dic_code", dictionary.getDicCode())
                .eq("index_name", dictionary.getIndexName());

        if(dictionary.getDicCode().contains("_erji_types")){
            queryWrapper = ((QueryWrapper<DictionaryEntity>) queryWrapper).eq("super_id",dictionary.getSuperId());
        }
        logger.info("sql语句:"+queryWrapper);
        DictionaryEntity dictionaryEntity = dictionaryService.getOne(queryWrapper);
        if(dictionaryEntity==null){
            dictionaryService.updateById(dictionary);
            //如果字典表修改数据的话,把数据再重新查出,放入监听器中
            List<DictionaryEntity> dictionaryEntities = dictionaryService.list(new QueryWrapper<DictionaryEntity>());
            ServletContext servletContext = request.getServletContext();
            Map<String, Map<Integer,String>> map = new HashMap<>();
            for(DictionaryEntity d :dictionaryEntities){
                Map<Integer, String> m = map.get(d.getDicCode());
                if(m ==null || m.isEmpty()){
                    m = new HashMap<>();
                }
                m.put(d.getCodeIndex(),d.getIndexName());
                map.put(d.getDicCode(),m);
            }
            servletContext.setAttribute("dictionaryMap",map);
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
        dictionaryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 最大值
     */
    @RequestMapping("/maxCodeIndex")
    public R maxCodeIndex(@RequestBody DictionaryEntity dictionary){
        logger.debug("maxCodeIndex:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());
        Wrapper<DictionaryEntity> queryWrapper = new QueryWrapper<DictionaryEntity>()
                .eq("dic_code", dictionary.getDicCode())
                .orderByDesc("code_index");
        logger.info("sql语句:"+queryWrapper);
        List<DictionaryEntity> dictionaryEntityList = dictionaryService.list(queryWrapper);
        if(dictionaryEntityList != null && dictionaryEntityList.size() > 0){
            return R.ok().put("maxCodeIndex",dictionaryEntityList.get(0).getCodeIndex()+1);
        }else{
            return R.ok().put("maxCodeIndex",1);
        }
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<DictionaryEntity> dictionaryList = new ArrayList<>();
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
                            DictionaryEntity dictionaryEntity = new DictionaryEntity();
                            //TODO: 填充字段
                            dictionaryList.add(dictionaryEntity);
                        }
                        dictionaryService.saveBatch(dictionaryList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }
}