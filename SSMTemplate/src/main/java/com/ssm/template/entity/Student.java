package com.ssm.template.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {
    String name;
    int sid;
}
