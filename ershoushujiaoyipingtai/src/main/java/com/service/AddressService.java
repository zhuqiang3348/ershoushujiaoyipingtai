package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.AddressEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 收货地址 服务类
 */
public interface AddressService extends IService<AddressEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}