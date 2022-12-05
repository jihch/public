package cn.stylefeng.guns.modular.business.question.controller;

import cn.stylefeng.guns.modular.business.question.entity.Question;
import cn.stylefeng.guns.modular.business.question.pojo.request.QuestionRequest;
import cn.stylefeng.guns.modular.business.question.service.QuestionService;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import cn.stylefeng.roses.kernel.dsctn.api.pojo.request.DatabaseInfoRequest;
import cn.stylefeng.roses.kernel.rule.annotation.BusinessLog;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@ApiResource(name = "面试题")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetResource(
            name = "查询题目列表（带分页）",
            path = {"/question/page"}
    )
    public ResponseData<PageResult<Question>> findPage(QuestionRequest questionRequest) {
        PageResult<Question> pageResult = this.questionService.findPage(questionRequest);
        return new SuccessResponseData(pageResult);
    }

    @PostResource(
            name = "新增面试题",
            path = {"/question/add"}
    )
    @BusinessLog
    public ResponseData<?> add(@RequestBody @Validated({BaseRequest.add.class}) QuestionRequest questionRequest) {
        this.questionService.add(questionRequest);
        return new SuccessResponseData();
    }

    @PostResource(
            name = "删除数据源",
            path = {"/question/delete"}
    )
    @BusinessLog
    public ResponseData<?> del(@RequestBody @Validated({BaseRequest.delete.class}) QuestionRequest questionRequest) {
        this.questionService.deleteByQuestionId(questionRequest.getId());
        return new SuccessResponseData();
    }



}
