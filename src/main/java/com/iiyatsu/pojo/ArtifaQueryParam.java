package com.iiyatsu.pojo;

import lombok.Data;

/**
 * 封装分页条件查询请求参数
 */
@Data
public class ArtifaQueryParam {
    private Long age;
    private String name;
    private Integer page = 1;
    private Integer pageSize = 10;
}
