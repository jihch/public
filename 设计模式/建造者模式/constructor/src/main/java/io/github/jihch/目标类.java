package io.github.jihch;

public class 目标类 {

    //目标类的构造方法要求传入 Builder 对象
    private 目标类(Builder builder) {

    }

    public 返回值 业务方法(参数列表) {

    }


    //Builder 建造者类位于目标类内部且用 static 描述
    public static class Builder {

        //Builder 建造者对象提供内置属性与各种 set 方法，注意 set 方法返回 Builder 对象本身
        private String xxx;

        public Builder setXxx(String xxx) {
            this.xxx = xxx;
            return this;
        }

        //Builder 建造者类提供 build() 方法实现目标类对象的创建
        public 目标类 build() {
            //业务校验
            return new 目标类(this);
        }


    }

}
