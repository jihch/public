package cn.stylefeng.guns.modular.business.question.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("question")
public class Question implements Serializable {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Long id;

    @ExcelProperty(
            value = {"题目"},
            index = 0
    )
    @TableField("topic")
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
