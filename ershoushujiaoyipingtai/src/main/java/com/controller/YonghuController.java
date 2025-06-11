package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
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
 * 用户
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/yonghu")
public class YonghuController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuController.class);

    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

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
        PageUtils page = yonghuService.queryPage(params);

        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list){
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
        YonghuEntity yonghu = yonghuService.getById(id);
        if(yonghu !=null){
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );
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
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YonghuEntity> queryWrapper = new QueryWrapper<YonghuEntity>()
                .eq("username", yonghu.getUsername())
                .or()
                .eq("yonghu_phone", yonghu.getYonghuPhone())
                .or()
                .eq("yonghu_id_number", yonghu.getYonghuIdNumber());

        logger.info("sql语句:"+queryWrapper);
        YonghuEntity yonghuEntity = yonghuService.getOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setCreateTime(new Date());
            yonghu.setPassword("123456");
            yonghuService.save(yonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者用户手机号或者用户身份证号已经被使用");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<YonghuEntity> queryWrapper = new QueryWrapper<YonghuEntity>()
                .ne("id",yonghu.getId())
                .and(wrapper -> wrapper
                        .eq("username", yonghu.getUsername())
                        .or()
                        .eq("yonghu_phone", yonghu.getYonghuPhone())
                        .or()
                        .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
                );

        logger.info("sql语句:"+queryWrapper);
        YonghuEntity yonghuEntity = yonghuService.getOne(queryWrapper);
        if("".equals(yonghu.getYonghuPhoto()) || "null".equals(yonghu.getYonghuPhoto())){
            yonghu.setYonghuPhoto(null);
        }
        if(yonghuEntity==null){
            yonghuService.updateById(yonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者用户手机号或者用户身份证号已经被使用");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),Arrays.toString(ids));
        yonghuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<YonghuEntity> yonghuList = new ArrayList<>();
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
                            YonghuEntity yonghuEntity = new YonghuEntity();
                            yonghuList.add(yonghuEntity);
                            if(seachFields.containsKey("username")){
                                List<String> username = seachFields.get("username");
                                username.add(data.get(0));
                            }else{
                                List<String> username = new ArrayList<>();
                                username.add(data.get(0));
                                seachFields.put("username",username);
                            }
                            if(seachFields.containsKey("yonghuPhone")){
                                List<String> yonghuPhone = seachFields.get("yonghuPhone");
                                yonghuPhone.add(data.get(0));
                            }else{
                                List<String> yonghuPhone = new ArrayList<>();
                                yonghuPhone.add(data.get(0));
                                seachFields.put("yonghuPhone",yonghuPhone);
                            }
                            if(seachFields.containsKey("yonghuIdNumber")){
                                List<String> yonghuIdNumber = seachFields.get("yonghuIdNumber");
                                yonghuIdNumber.add(data.get(0));
                            }else{
                                List<String> yonghuIdNumber = new ArrayList<>();
                                yonghuIdNumber.add(data.get(0));
                                seachFields.put("yonghuIdNumber",yonghuIdNumber);
                            }
                        }
                        List<YonghuEntity> yonghuEntities_username = yonghuService.list(new QueryWrapper<YonghuEntity>().in("username", seachFields.get("username")));
                        if(yonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        List<YonghuEntity> yonghuEntities_yonghuPhone = yonghuService.list(new QueryWrapper<YonghuEntity>().in("yonghu_phone", seachFields.get("yonghuPhone")));
                        if(yonghuEntities_yonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuPhone){
                                repeatFields.add(s.getYonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [用户手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        List<YonghuEntity> yonghuEntities_yonghuIdNumber = yonghuService.list(new QueryWrapper<YonghuEntity>().in("yonghu_id_number", seachFields.get("yonghuIdNumber")));
                        if(yonghuEntities_yonghuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuIdNumber){
                                repeatFields.add(s.getYonghuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [用户身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yonghuService.saveBatch(yonghuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.getOne(new QueryWrapper<YonghuEntity>().eq("username", username));
        if(yonghu==null || !yonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(yonghu.getId(),username, "yonghu", "用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","用户");
        r.put("username",yonghu.getYonghuName());
        r.put("tableName","yonghu");
        r.put("userId",yonghu.getId());
        return r;
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YonghuEntity yonghu){
        Wrapper<YonghuEntity> queryWrapper = new QueryWrapper<YonghuEntity>()
                .eq("username", yonghu.getUsername())
                .or()
                .eq("yonghu_phone", yonghu.getYonghuPhone())
                .or()
                .eq("yonghu_id_number", yonghu.getYonghuIdNumber());
        YonghuEntity yonghuEntity = yonghuService.getOne(queryWrapper);
        if(yonghuEntity != null)
            return R.error("账户或者用户手机号或者用户身份证号已经被使用");
        yonghu.setNewMoney(0.0);
        yonghu.setCreateTime(new Date());
        yonghuService.save(yonghu);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        YonghuEntity yonghu = new YonghuEntity();
        yonghu.setPassword("123456");
        yonghu.setId(id);
        yonghuService.updateById(yonghu);
        return R.ok();
    }

    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.getOne(new QueryWrapper<YonghuEntity>().eq("username", username));
        if(yonghu!=null){
            yonghu.setPassword("123456");
            boolean b = yonghuService.updateById(yonghu);
            if(!b){
                return R.error();
            }
        }else{
            return R.error("账号不存在");
        }
        return R.ok();
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrYonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YonghuEntity yonghu = yonghuService.getById(id);
        if(yonghu !=null){
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }

    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = yonghuService.queryPage(params);
        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuEntity yonghu = yonghuService.getById(id);
        if(yonghu !=null){
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );
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
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        Wrapper<YonghuEntity> queryWrapper = new QueryWrapper<YonghuEntity>()
                .eq("username", yonghu.getUsername())
                .or()
                .eq("yonghu_phone", yonghu.getYonghuPhone())
                .or()
                .eq("yonghu_id_number", yonghu.getYonghuIdNumber());
        logger.info("sql语句:"+queryWrapper);
        YonghuEntity yonghuEntity = yonghuService.getOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setCreateTime(new Date());
            yonghu.setPassword("123456");
            yonghuService.save(yonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者用户手机号或者用户身份证号已经被使用");
        }
    }
}