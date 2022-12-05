package cn.stylefeng.guns.modular.business.question.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;

@Controller
@ApiResource(
        name = "面试题界面"
)
public class QuestionViewController {

    @GetResource(
            name = "系统配置-列表-视图",
            path = {"/view/question"}
    )
    public String indexView() {
        return "/modular/business/question/question.html";
    }

    @GetResource(
            name = "多数据源新增界面",
            path = {"/view/question/add"}
    )
    public String addView() {
        return "/modular/business/question/question_add.html";
    }
}
