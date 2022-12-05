package cn.stylefeng.guns.modular.business.question.api;

import cn.stylefeng.guns.modular.business.question.pojo.request.QuestionRequest;
import cn.stylefeng.roses.kernel.dsctn.api.pojo.request.DatabaseInfoRequest;

public interface QuestionApi {

    void add(QuestionRequest questionRequest);

    void deleteByQuestionId(Long questionId);

}
