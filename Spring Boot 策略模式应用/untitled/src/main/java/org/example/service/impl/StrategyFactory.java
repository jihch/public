package org.example.service.impl;

import org.example.service.BusinessStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 策略工厂：根据前端参数获取对应的Service
 */
@Service
public class StrategyFactory {
    // Spring自动注入所有实现了BusinessStrategy接口的Bean
    private final Map<String, BusinessStrategy> strategyMap;

    // 构造函数注入：key为Bean名称，value为Bean实例
    public StrategyFactory(List<BusinessStrategy> strategyList) {
        strategyMap = new HashMap<>();
        // 将策略类按type存入Map，方便快速获取
        for (BusinessStrategy strategy : strategyList) {
            strategyMap.put(strategy.getStrategyType(), strategy);
        }
    }

    /**
     * 根据前端传入的type获取对应的Service
     * @param type 前端参数（如user/order/product）
     * @return 对应的业务处理Service
     * @throws IllegalArgumentException 无匹配策略时抛出异常
     */
    public BusinessStrategy getStrategy(String type) {
        BusinessStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的业务类型：" + type);
        }
        return strategy;
    }
}
