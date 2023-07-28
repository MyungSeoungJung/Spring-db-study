package com.tje.controller.account;


//서버란 
 // ㄴ> 컴퓨터
//      서비스를 제공해주는 컴퓨터
//      서비스란 요청에 대한 적절할 응답을 제공해줌

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 서버 프로그램 (백엔드,backend)
//      ㄴ> 요청에 대한 적절할 응답을 제공해주는 프로그램
@RestController
//컨트롤러에 RequestMapping을 사용하여
//value 경로의 요청을 컨트롤러 내부의 매서드로 실행
//GET.... /accounts -> AccountController -> method(GET요청을 처리할 메서드)
@RequestMapping(value = "/accounts")
public class AccountController {


    public void func(){
        Account acc = new Account("001","명승정",10000,new Date().getTime());
//        intellij에서 룸복 라이브러리를 사용중이면 getter/setter등을 쓸 수 있게 해주는 것
        acc.getAno();
        acc.setAno("002");
    }

    List<Account> list = new ArrayList<>();

//
    @GetMapping
    public List<Account> getAccountList(){
//        전체 필드 매개변수 생성자로 생성
//        매개변수 순서가 필드 순서와 같음
        list.add(new Account("0001","명승정",250045,new Date().getTime()));
//        빌더 패턴으로 만듬 => 순서가 상관이 없음
        list.add(Account.builder().ownerName("김철수").ano("0002").balance(12455).createTime(new Date().getTime()).build());
        return list;
    }
}
