package com.tje.controller.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {
    //    동시처리에 대한 지원을 해주는 자료구조
//    여러명의 유저들이 같은 데이터를 접근 할 수 있음.
//    데이터베이스는 기본적으로 동시성에 대한 구현이 있음
    Map<String, Contact> map = new ConcurrentHashMap<>();

    // GET /contacts
    @GetMapping
    public List<Contact> getContactList() {
//        Key가 이메일
//        map.put("audtmdwjd@naver.com", Contact.builder()
//                .name("명승정").phone("010-4542-1644")
//                .email("audtmdwjd@naver.com").build());
//        map.put("dlthtjr@naver.com", Contact.builder()
//                .name("이소석").phone("010-6565-0622")
//                .email("dlthtjr@naver.com").build());

        var list = new ArrayList<>(map.values());
//        list.sort((a, b)-> b.getId() - a.getId());

//        list.sort((a, b)-> a.getName().compareTo(b.getName()));
        list.sort(Comparator.comparing(Contact::getName));

        return list;
    }


// HTTP 1.1 POST /contacts
    // HTTP버전 메서드 리소스URL
    // : Request Line

    // Content-Type: application/json - 요청보낼 데이터 형식
    // Accept: */* - 서버로 부터 어떤 형식의 데이터를 받을지
    //         ex) image/jpeg, application/json, text/html, plain/text
    // : Request Header
    // : 서버에 부가적인 요청정보

    // {"name":"홍길동", "phone":"010-1234-5678", "email":"hong@gmail.com"}
    // :Request Body(요청 본문)

    // JSON: 문자열, 자바스크립트 객체 표기법
    // Client(브라우저) Request Header에 Content-Type: application/json
    // Request Body가 JSON 문자열이면

    // Server(스프링) @RequestBody를 메서드 매개변수에 넣어주면
    // JSON(문자열) -> Java객체로 변환
    @PostMapping
    public ResponseEntity<Map<String, Object>> addContact(@RequestBody Contact contact) {
        // 클라이언트에서 넘어온 JSON이 객체로 잘 변환됐는지 확인
        System.out.println(contact.getName());
        System.out.println(contact.getPhone());
        System.out.println(contact.getEmail());

        // 이메일 필수값 검증
        // 400: bad request
        if (contact.getEmail() == null || contact.getEmail().isEmpty()) {
            // 응답 객체 생성
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "[email] field is required");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }

        // 이메일 형식 검증
        // 400: bad request

        // 이메일 중복 검증
        // 409: conflict
        //                         map에서 email값을 불러온 다음 null이 아니면 중복이라는 뜻
        if (contact.getEmail() != null && map.get(contact.getEmail()) != null) {
            // 맵에 해당 이메일이 있음
            // 이미 있는 데이터를 클라이언트(브라우저) 보냈거나
            // 클라이언트에서 중복 데이터를 보냈거나..
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "동일한 정보가 이미 존재합니다.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        // 맵에 객체 추가
        map.put(contact.getEmail(), contact);

        // 응답 객체 생성
//        실제로 생성된 객체를 응답
        Map<String, Object> res = new HashMap<>();
        res.put("data", map.get(contact.getEmail()));
        res.put("message", "created");

        // HTTP Status Code: 201 Created
        // 리소스가 정상적으로 생성되었음.
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

    //   그냥 delete하면 다 삭제되니까 특정 값 선택
//      DELETE /contacts/{email}
//      DELETE /contacts/audtmdwjd@naver.com
    @DeleteMapping(value = "/{email}")
//    PathVariable("email")             /경로 문자열{email}과 변수명 String email이 동일하면 ("email")을 굳이 안써도된다
    public ResponseEntity removeContact(@PathVariable("email") String email) {

        System.out.println(email);
//        해당 키의 데이터가 없으면
        if (map.get(email) == null) {
            //404: NOT FOUND, 해당 경로에 리소스가 없다 반환
//           4로 시작하는 에러는 클라이언트 에러
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
//      객체 삭제
        map.remove(email);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}