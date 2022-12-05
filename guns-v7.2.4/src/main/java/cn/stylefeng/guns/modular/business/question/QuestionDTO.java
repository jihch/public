package cn.stylefeng.guns.modular.business.question;

import cn.stylefeng.roses.kernel.rule.annotation.ChineseDescription;

public class QuestionDTO {

    @ChineseDescription("主键")
    private Long id;

    @ChineseDescription("题目")
    private String topic;

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
