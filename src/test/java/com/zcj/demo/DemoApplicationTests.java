package com.zcj.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcj.demo.service.UserService;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)// 1.4.0 前版本
@SpringBootTest(classes = DemoApplication.class)//指定启动类
//@SpringApplicationConfiguration(classes = Application.class)// 1.4.0 前版本
//@WebAppConfiguration
@Transactional
public class DemoApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    UserService userService;
    private MockMvc mvc;

    @Test
    public void testOne() {
        System.out.println("test hello 1");
    }

    @Test
    public void testTwo() {
        System.out.println("test hello 2");
        TestCase.assertEquals(1, 1);
    }

    //在所有测试方法前执行一次，一般在其中写上整体初始化的代码
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("BeforeClass");
    }

    //在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码
    @AfterClass
    public static void testAfterClass() {
        System.out.println("afterClass");
    }

    //在每个测试方法前执行，一般用来初始化方法（比如我们在测试别的方法时，类中与其他测试方法共享的值已经被改变，
    //为了保证测试结果的有效性，我们会在@Before注解的方法中重置数据）
    @Before
    public void testBefore() {
        System.out.println("before");
    }

    //在每个测试方法后执行，在方法执行完成后要做的事情
    @After
    public void testAfter() {
        System.out.println("after");
    }

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();//该方法产生了空指针异常，找不到对应的service类
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     * 比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
     */
    @Test
    public void contextLoads() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/user/test")
                .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        JSONObject result = JSON.parseObject(content);
        String str = result.getString("data");
        System.out.println(str);
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        Assert.assertEquals("测试修改", str);            //断言，判断返回的值是否正确
    }

}
