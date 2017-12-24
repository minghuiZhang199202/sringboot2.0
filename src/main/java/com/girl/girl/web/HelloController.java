package com.girl.web;

import com.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author  zmh
 * date 2017-12-8 22:22:44
 * modified by
 */
@RestController
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;


    /**
     * <p>description: </p>
     * <p>{name}的位置可以放在/hello/{name}或者/{name}/hello/</p>
     * @param name 姓名
     * @author minghuiZhang
     * date 23:38 2017/12/12
     *
     */
    @RequestMapping(value = "/hello/{name}",method = RequestMethod.GET)
    public String helloP(@PathVariable("name") String name){

        return "Hello " + name;
    }
    /**
     * <p>description: </p>
     * @param name 姓名
     * @author minghuiZhang
     * date 23:36 2017/12/12
     *
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloR(@RequestParam(value = "name",defaultValue = "zmh") String name){

        return "Hello " + name;
    }

    @RequestMapping(value = {"/hello/Page","/hi"},method = RequestMethod.GET)
    public String helloPage(){
        return "index";
    }
}
