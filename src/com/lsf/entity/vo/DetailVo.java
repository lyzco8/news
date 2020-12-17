package com.lsf.entity.vo;

import com.lsf.entity.Detail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 刘愿
 * @date 2020/12/9 20:38
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DetailVo extends Detail {
    private String name;
    private String username;
}
