package com.controller;

import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 用户反馈
 * 后端接口
 * @author
 * @email
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private YonghuService yonghuService;

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChatEntity chat, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            chat.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ChatEntity> queryWrapper = new QueryWrapper<ChatEntity>()
                .eq("yonghu_id", chat.getYonghuId())
                .eq("chat_issue", chat.getChatIssue())
                .eq("chat_reply", chat.getChatReply())
                .eq("zhuangtai_types", chat.getZhuangtaiTypes())
                .eq("chat_types", chat.getChatTypes());

        logger.info("sql语句:"+queryWrapper);
        ChatEntity chatEntity = chatService.getOne(queryWrapper);
        if(chatEntity==null){
            chat.setInsertTime(new Date());
            chatService.save(chat);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ChatEntity chat, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        Wrapper<ChatEntity> queryWrapper = new QueryWrapper<ChatEntity>()
                .ne("id",chat.getId())
                .eq("yonghu_id", chat.getYonghuId())
                .eq("chat_issue", chat.getChatIssue())
                .eq("chat_reply", chat.getChatReply())
                .eq("zhuangtai_types", chat.getZhuangtaiTypes())
                .eq("chat_types", chat.getChatTypes());

        logger.info("sql语句:"+queryWrapper);
        ChatEntity chatEntity = chatService.getOne(queryWrapper);
        if(chatEntity==null){
            chatService.updateById(chat);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = chatService.queryPage(params);
        List<ChatView> list =(List<ChatView>)page.getList();
        for(ChatView c:list){
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
        ChatEntity chat = chatService.getById(id);
        if(chat !=null){
            ChatView view = new ChatView();
            BeanUtils.copyProperties(chat, view);

            //级联表
            YonghuEntity yonghu = yonghuService.getById(chat.getYonghuId());
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
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),Arrays.toString(ids));
        chatService.removeByIds(Arrays.asList(ids));
        return R.ok();
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
        PageUtils page = chatService.queryPage(params);
        List<ChatView> list =(List<ChatView>)page.getList();
        for(ChatView c:list)
            dictionaryService.dictionaryConvert(c, request);
        return R.ok().put("data", page);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChatEntity chat = chatService.getById(id);
        if(chat !=null){
            ChatView view = new ChatView();
            BeanUtils.copyProperties(chat, view);

            //级联表
            YonghuEntity yonghu = yonghuService.getById(chat.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties(yonghu, view, new String[]{ "id", "createTime"});
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
    public R add(@RequestBody ChatEntity chat, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());
        Wrapper<ChatEntity> queryWrapper = new QueryWrapper<ChatEntity>()
                .eq("yonghu_id", chat.getYonghuId())
                .eq("chat_issue", chat.getChatIssue())
                .eq("chat_reply", chat.getChatReply())
                .eq("zhuangtai_types", chat.getZhuangtaiTypes())
                .eq("chat_types", chat.getChatTypes());
        logger.info("sql语句:"+queryWrapper);
        ChatEntity chatEntity = chatService.getOne(queryWrapper);
        if(chatEntity==null){
            chat.setInsertTime(new Date());
            chatService.save(chat);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
}