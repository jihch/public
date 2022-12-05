package cn.stylefeng.guns.modular.business.question.service;

import cn.stylefeng.guns.modular.business.question.api.QuestionApi;
import cn.stylefeng.guns.modular.business.question.entity.Question;
import cn.stylefeng.guns.modular.business.question.pojo.request.QuestionRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;


public interface QuestionService extends IService<Question>, QuestionApi {

    PageResult<Question> findPage(QuestionRequest questionRequest);

}
