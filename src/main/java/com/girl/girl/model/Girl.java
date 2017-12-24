package com.girl.model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:24 / 2017/12/12</p>
 * <p>modified by   </p>
 */
@Data
@Entity

public class Girl {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String cupSize;
    @Min(value = 18,message = "未成年少女禁止入内")
    @NotNull(message = "年龄必填")
    private Integer age;
    @NotNull(message = "金额必填")
    private Float money;
}
