package org.example.service.impl;

import org.example.service.BusinessStrategy;
import org.example.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单相关业务处理Service
 */
@Service
public class OrderServiceImpl implements IOrderService, BusinessStrategy {
    @Override
    public String handleBusiness(Map<String, Object> param) {
        // 处理订单相关业务逻辑
        String orderId = (String) param.get("orderId");
        return "处理订单业务，订单ID：" + orderId;
    }

    @Override
    public String getStrategyType() {
        // 对应前端传入的type=order
        return "order";
    }
}
