package org.example.service;

import java.util.Map;

/**
 * 业务处理策略接口
 */
public interface BusinessStrategy {
    /**
     * 统一的业务处理方法
     * @param param 前端传入的参数
     * @return 处理结果
     */
    String handleBusiness(Map<String, Object> param);

    /**
     * 获取当前策略对应的标识（与前端传参匹配）
     * @return 策略标识
     */
    String getStrategyType();
}
