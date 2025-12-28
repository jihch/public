package org.example.service.impl;

import org.example.service.BusinessStrategy;
import org.example.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品相关业务处理Service
 */
@Service
public class ProductServiceImpl implements IProductService, BusinessStrategy {

    @Override
    public String handleBusiness(Map<String, Object> param) {
        // 处理商品相关业务逻辑
        String productId = (String) param.get("productId");
        return "处理商品业务，商品ID：" + productId;
    }

    @Override
    public String getStrategyType() {
        // 对应前端传入的type=product
        return "product";
    }

}

