package com.girl.persistence;

import com.girl.model.Girl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:45 / 2017/12/12</p>
 * <p>modified by   </p>
 */
@CacheConfig(cacheNames = "girl")
public interface GirlRepository extends JpaRepository<Girl,Integer> {
    /**
     * <p>此处方法命名格式固定，按照年龄查询必须为findByAge</p>
     * <p>minghuiZhang 2017-12-12 23:24:55</p>
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
