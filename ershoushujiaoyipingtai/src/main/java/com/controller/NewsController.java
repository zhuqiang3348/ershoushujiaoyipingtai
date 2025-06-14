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
 * 公告信息
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = newsService.queryPage(params);

        List<NewsView> list =(List<NewsView>)page.getList();
        for(NewsView c:list){
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
        NewsEntity news = newsService.getById(id);
        if(news !=null){
            NewsView view = new NewsView();
            BeanUtils.copyProperties(news, view);
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
    public R save(@RequestBody NewsEntity news, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,news:{}",this.getClass().getName(),news.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<NewsEntity> queryWrapper = new QueryWrapper<NewsEntity>()
                .eq("news_name", news.getNewsName())
                .eq("news_types", news.getNewsTypes());

        logger.info("sql语句:"+queryWrapper);
        NewsEntity newsEntity = newsService.getOne(queryWrapper);
        if(newsEntity==null){
            news.setCreateTime(new Date());
            newsService.save(news);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NewsEntity news, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,news:{}",this.getClass().getName(),news.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<NewsEntity> queryWrapper = new QueryWrapper<NewsEntity>()
                .ne("id",news.getId())
                .eq("news_name", news.getNewsName())
                .eq("news_types", news.getNewsTypes());

        logger.info("sql语句:"+queryWrapper);
        NewsEntity newsEntity = newsService.getOne(queryWrapper);
        if("".equals(news.getNewsPhoto()) || "null".equals(news.getNewsPhoto())){
            news.setNewsPhoto(null);
        }
        if(newsEntity==null){
            newsService.updateById(news);//根据id更新
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
        newsService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<NewsEntity> newsList = new ArrayList<>();
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
                            NewsEntity newsEntity = new NewsEntity();
                            // TODO: 填充字段
                            newsList.add(newsEntity);
                        }
                        newsService.saveBatch(newsList);
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
        PageUtils page = newsService.queryPage(params);

        List<NewsView> list =(List<NewsView>)page.getList();
        for(NewsView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        NewsEntity news = newsService.getById(id);
        if(news !=null){
            NewsView view = new NewsView();
            BeanUtils.copyProperties(news, view);
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
    public R add(@RequestBody NewsEntity news, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,news:{}",this.getClass().getName(),news.toString());
        Wrapper<NewsEntity> queryWrapper = new QueryWrapper<NewsEntity>()
                .eq("news_name", news.getNewsName())
                .eq("news_types", news.getNewsTypes());
        logger.info("sql语句:"+queryWrapper);
        NewsEntity newsEntity = newsService.getOne(queryWrapper);
        if(newsEntity==null){
            news.setCreateTime(new Date());
            newsService.save(news);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
}