package com.girl.service;

import com.girl.enums.ResultEnum;
import com.girl.girlexception.GirlException;
import com.girl.model.Girl;
import com.girl.persistence.GirlRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 8:43 2017/12/13</p>
 * <p>modified By: </p>
 */
@Service
@Slf4j
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    /**
     * <p>description：插入两个girl</p>
     * @param
     * @author: minghuiZhang.
     * @date: created in 9:01 2017/12/13
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveTwo(){
        log.info("sdfsd");
        Girl girlA  = new Girl();
        girlA.setName("李艳");
        girlA.setAge(24);
        girlA.setCupSize("A");
        Girl girlB  = new Girl();
        girlB.setName("肖华");
        girlB.setAge(27);
        girlB.setCupSize("CC");
        girlRepository.save(girlA);
        girlRepository.save(girlB);
    }


    public void getAge(Integer id) throws Exception{
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10){
            //code 100
           throw new GirlException(ResultEnum.GO_PRIMARY);
        }else if (age >= 10 && age < 16){
            //code 101
            throw new GirlException(ResultEnum.GO_JUNIOR_SCHOOL);
        }

    }
    /**
     * <p>description：通过女生查看一个女生的信息</p>
     * @param id 女生id
     * @author: minghuiZhang.
     * @date: created in 17:07 2017/12/13
     */
    @Cacheable(value ="girl",key = "#id" )
    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 按照年龄查询girl
     * @param age
     * @return
     */
    @Cacheable(value = "girl",key = "#age")
    public List<Girl> findByAge(Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 查询所有女生
     * @return
     */
    @Cacheable(value = "girl")
    public List<Girl> findAll(){
        return girlRepository.findAll();
    }
    @CachePut(value = "girl")
    public Girl save(Girl girl){
        return girlRepository.save(girl);
    }

    @CacheEvict(value = "girl",key = "#id")
    public void delete(Integer id) {
        girlRepository.delete(id);
    }

    @CacheEvict(value = "girl", allEntries = true) // 移除所有数据
    public void deleteAll() {
        girlRepository.deleteAll();
    }

}
