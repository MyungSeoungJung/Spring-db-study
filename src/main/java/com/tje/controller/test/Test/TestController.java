package com.tje.controller.test.Test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Controller
// = @Compo

@RestController
public class TestController {


    //http://localhost:8080/hello
// localhost(본인 컴퓨터), 8080 포트번호 접속

//    /hello , 경로(path)에 대한 요청(request..에 대한 응답)
//    HTTP method: GET,POST,DELETE,OPTIONS,PATCH,HEAD
    // 주소창에 주소를 치고 입력하고 엔터치면 => 요청에 대한 응답이 나옴  이걸-> GET(조회) http://localhost:8080/hello

//    Dispatcher Servlet이 요청 경로에 맞는 메서드를 호출함
//    Dispatcher (디스패쳐): 중간에서 요청에서 분기를 해주고 응답을 통하여 처리
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return  "Hello,spring frameWork";
    }
}
