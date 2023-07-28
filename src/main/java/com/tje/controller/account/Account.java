package com.tje.controller.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;


@Data //getter,setter 자동으로 만들어줌, 컴파일 시점에
@Builder
@AllArgsConstructor

public class Account {

    private String ano;
    private String ownerName;
    private long balance;
    private long createTime;
}
