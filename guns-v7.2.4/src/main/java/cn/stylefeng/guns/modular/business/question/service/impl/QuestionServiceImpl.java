package cn.stylefeng.guns.modular.business.question.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.modular.business.question.entity.Question;
import cn.stylefeng.guns.modular.business.question.mapper.QuestionMapper;
import cn.stylefeng.guns.modular.business.question.pojo.request.QuestionRequest;
import cn.stylefeng.guns.modular.business.question.service.QuestionService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public PageResult<Question> findPage(QuestionRequest questionRequest) {
        LambdaQueryWrapper<Question> queryWrapper = this.createWrapper(questionRequest);
        Page<Question> result = (Page)this.page(PageFactory.defaultPage(), queryWrapper);
        return PageResultFactory.createPageResult(result);
    }

    private LambdaQueryWrapper<Question> createWrapper(QuestionRequest questionRequest) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper();
        if (ObjectUtil.isEmpty(questionRequest)) {
            return queryWrapper;
        } else {
            String topic = questionRequest.getTopic();
            queryWrapper.like(ObjectUtil.isNotEmpty(topic), Question::getTopic, topic);
            return queryWrapper;
        }
    }

    @Override
    public void add(QuestionRequest questionRequest) {
        Question entity = new Question();
        BeanUtil.copyProperties(questionRequest, entity, new String[0]);
        this.save(entity);
    }

    @Override
    public void deleteByQuestionId(Long questionId) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ObjectUtil.isNotEmpty(questionId), Question::getId, questionId);
        this.remove(queryWrapper);
    }

}
