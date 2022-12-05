package cn.stylefeng.guns.modular.business.question.pojo.request;

import cn.stylefeng.roses.kernel.rule.annotation.ChineseDescription;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import cn.stylefeng.roses.kernel.system.api.pojo.user.request.SysUserRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionRequest extends BaseRequest {

    @ChineseDescription("主键")
    private @NotNull(
            message = "id不能为空",
            groups = {BaseRequest.edit.class, BaseRequest.delete.class, BaseRequest.detail.class}
    ) Long id;

    @ChineseDescription("题目")
    private @NotBlank(
            message = "题目",
            groups = {BaseRequest.add.class, BaseRequest.edit.class, SysUserRequest.reg.class}
    ) String topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
