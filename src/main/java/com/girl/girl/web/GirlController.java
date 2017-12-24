package com.girl.web;

import com.girl.model.Girl;
import com.girl.model.Result;
import com.girl.service.GirlService;
import com.zmh.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:41 / 2017/12/12</p>
 * <p>modified by   </p>
 */
@RestController
@Slf4j
public class GirlController{
    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生
     * minghuiZhang 2017-12-12 22:56:10
     * @return 返回所有女生
     */
    @GetMapping(value = "/girls")
    public Result<Girl> findAll(){
        log.info("absolute_path:{}",ClassUtils.getDefaultClassLoader().getResource("").getPath());
        return ResultUtil.success(girlService.findAll());

    }

    /**
     * 添加一个女生 对象型
     * <p>加入表单验证功能，年龄不能小于18岁</p>
     * @param girl 女生对象
     * @return 返回添加后的女生
     */
    @PostMapping(value = "/girlAdd")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.info(bindingResult.getFieldError().toString());
            return ResultUtil.failure(1,bindingResult.getFieldError().getDefaultMessage());
        }

        return  ResultUtil.success(girlService.save(girl));
    }

    /**
     * 查找一个女生
     * @param id 女生id
     * @return 返回查询女生
     */
    @GetMapping(value = "/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlService.findOne(id);
    }

    /**
     * <p>按照年龄查询一个女生</p>
     * <p>自定义</p>
     * <p>minghuiZhang 2017-12-12 23:23:08</p>
     * @param age 年龄
     * @return 返回age年龄下的所有女生
     */
    @GetMapping(value = "/age/{age}")
    public List<Girl> findOneByAge(@PathVariable("age") Integer age){
        return girlService.findByAge(age);
    }



    @DeleteMapping(value = "/delete/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
         girlService.delete(id);
    }

    @PostMapping(value = "/two")
    public void girlAddTwo(){
        girlService.saveTwo();
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id)throws Exception{
        log.info("");
        girlService.getAge(id);
    }


    @GetMapping(value = "/deleteAll")
    public void deleteAll(){
        girlService.deleteAll();
    }
}

