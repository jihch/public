package org.example.controller;

import org.example.service.BusinessStrategy;
import org.example.service.impl.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 前端接口控制器
 */
@RestController
@RequestMapping("/api/business")
public class BusinessController {
    private final StrategyFactory strategyFactory;

    @Autowired
    public BusinessController(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * 统一的业务处理接口
     * @param type 业务类型（前端传参：user/order/product）
     * @param param 其他业务参数
     * @return 处理结果
     */
    @PostMapping("/handle")
    public ResponseEntity<String> handleBusiness(
            @RequestParam String type,
            @RequestBody Map<String, Object> param) {
        try {
            // 1. 根据type获取对应的Service
            BusinessStrategy strategy = strategyFactory.getStrategy(type);
            // 2. 调用对应的业务处理方法
            String result = strategy.handleBusiness(param);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
