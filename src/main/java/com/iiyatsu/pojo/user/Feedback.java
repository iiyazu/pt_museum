package com.iiyatsu.pojo.user;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Feedback {
    private Integer id;
    private String userId;
    private String content;
    private LocalDateTime createTime;
    private FStatus status = FStatus.PENDING; // 使用内部枚举

    // 定义内部枚举类
    public enum FStatus {
        PENDING("待处理"),
        ACCEPTED("已接受"),
        REJECTED("已拒绝");

        private final String description;

        FStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
