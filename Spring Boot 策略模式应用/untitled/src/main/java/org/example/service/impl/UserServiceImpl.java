package org.example.service.impl;

import org.example.service.BusinessStrategy;
import org.example.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户相关业务处理Service
 */
@Service
public class UserServiceImpl implements IUserService, BusinessStrategy {

    @Override
    public String handleBusiness(Map<String, Object> param) {
        // 处理用户相关业务逻辑
        String userId = (String) param.get("userId");
        return "处理用户业务，用户ID：" + userId;
    }

    @Override
    public String getStrategyType() {
        // 对应前端传入的type=user
        return "user";
    }
}
