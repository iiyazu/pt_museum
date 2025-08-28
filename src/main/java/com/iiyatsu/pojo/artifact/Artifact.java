package com.iiyatsu.pojo.artifact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {
    private Integer id;
    private String name;
    private String image;
    private String description;
    private Integer age;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
