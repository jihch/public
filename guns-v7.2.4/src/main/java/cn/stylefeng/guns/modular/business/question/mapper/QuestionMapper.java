package cn.stylefeng.guns.modular.business.question.mapper;

import cn.stylefeng.guns.modular.business.question.QuestionDTO;
import cn.stylefeng.guns.modular.business.question.entity.Question;
import cn.stylefeng.guns.modular.business.question.pojo.request.QuestionRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {


}
