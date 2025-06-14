
package com.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.TokenEntity;
import com.utils.PageUtils;


/**
 * token
 * @author yangliyuan
 * @date 2019年10月10日 上午9:18:20
 */
public interface TokenService extends IService<TokenEntity> {
 	PageUtils queryPage(Map<String, Object> params);
    
   	List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TokenEntity> wrapper);
	
   	String generateToken(Integer userid,String username,String tableName, String role);
   	
   	TokenEntity getTokenEntity(String token);
}
