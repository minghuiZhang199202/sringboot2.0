package com.girl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:47 / 2017/12/8</p>
 * <p>modified by   </p>
 */
@Component
@ConfigurationProperties(prefix = "girl")
@Data
public class GirlProperties {
    private String cupSize;
    private Integer age;
    private String content;
}
