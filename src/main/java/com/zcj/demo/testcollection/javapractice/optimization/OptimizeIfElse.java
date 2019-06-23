package com.zcj.demo.testcollection.javapractice.optimization;

import com.zcj.demo.model.User;

import java.util.Optional;

/**
 * @Auther: 10062376
 * @Date: 2019/5/28 09:42
 * @Description: 对于代码中if else以及大量的switch case语句，我们可以用以下几种方式代替和优化
 */
public class OptimizeIfElse {
    public static void main(String[] args) {
        //1、使用枚举的方式对if else语句进行优化
        int statusCode = Status.valueOf("NEW").statusCode;
        System.out.println("枚举的方式: " + statusCode);
        System.out.println(testOptional(new User()));
        System.out.println(getMonthDays(7));
    }

    public enum Status {
        NEW(0), RUNNABLE(1), RUNNING(2), BLOCKED(3), DEAD(4);

        public int statusCode;

        Status(int statusCode) {
            this.statusCode = statusCode;
        }
    }

    //2、optional可以让非空校验更加优雅，间接的减少if操作。
    //补充：Java 9 为 Optional 类添加了三个方法：or()、ifPresentOrElse() 和 stream()
    public static String testOptional(User user) {
        //尝试访问 emptyOpt 变量的值会导致 NoSuchElementException
//        Optional<User> userOptional0 = Optional.empty();
//        System.out.println(userOptional0.get());
        //可以使用  of() 和 ofNullable() 方法创建包含值的 Optional。
        // 两个方法的不同之处在于如果你把 null 值作为参数传递进去，of() 方法会抛出 NullPointerException
//        Optional<User> userOptional1 = Optional.of(user);
//        System.out.println(userOptional1.get());

        //应该明确对象不为 null  的时候使用 of()。
        //如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法
        Optional<User> userOptional = Optional.ofNullable(user);
        User user2 = userOptional.get();
        System.out.println("可以判断是否有值: "+userOptional.isPresent());
        //该方法在user有值的时候执行
        userOptional.ifPresent(user1 -> user1.getUserName());
        String str0 = userOptional.map(user1 -> user.getUserName()).orElse("用户数据为空！");
        String str1 = userOptional.ofNullable(user.getUserName()).orElse("用户数据为空！");
        String str2 = userOptional.ofNullable(user.getUserName()).orElseGet(() -> "用户数据为空！");
        //optional还可以返回异常
//        userOptional.ofNullable(user.getUserName()).orElseThrow(()->new IllegalArgumentException());

        return str2;
    }
    public static void action1(){
        System.out.println("do action1");
    }
    public void action2(){
        System.out.println("do action2");
    }

    //3、表驱动法是一种编程模式，它的本质是，从表里查询信息来代替逻辑语句(if,case)
    static int monthDays[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int getMonthDays(int month){
        return monthDays[--month];
    }
    public void mapTest(){
        /*Map<?,Function<?> action> actionsMap = new HashMap<>();

        // 初试配置对应动作
        actionsMap.put(value1, (someParams) -> { doAction1(someParams)});
        actionsMap.put(value2, (someParams) -> { doAction2(someParams)});
        actionsMap.put(value3, (someParams) -> { doAction3(someParams)});

        // 省略 null 判断
        actionsMap.get(param).apply(someParams); */
    }



}
