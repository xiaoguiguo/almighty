package com.test.spring.framework.log.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuditLog {
    private Long id;
    private String userName;
    private String action;
    private Long userId;
    private String ip;
    private Long actionTime;
    private String url;
    private String failReason;
    private String loginEmail;
    private String reqParameter;
    private String reqBody;
    private String uuid;
    private String status;
    private String userType;
}
