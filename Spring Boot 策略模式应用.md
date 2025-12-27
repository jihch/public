步骤 1：定义策略接口

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

步骤 2：实现具体的 Service（策略实现类）
/**
 * 用户相关业务处理Service
 */
@Service
public class UserService implements BusinessStrategy {
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

/**
 * 订单相关业务处理Service
 */
@Service
public class OrderService implements BusinessStrategy {
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

/**
 * 商品相关业务处理Service
 */
@Service
public class ProductService implements BusinessStrategy {
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

步骤 3：创建策略工厂（核心）
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

步骤 4：控制器层调用
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