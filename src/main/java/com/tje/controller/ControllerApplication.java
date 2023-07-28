package com.tje.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration
	// ㄴ> 자동화 설정을 쓰겠다.
		//의존성 라이브러리에 맞는 자동화된 기본 설정을 해줌
		// spring-web, spring-test
		//웹서버 띄워주고, 테스트 코드를 실행 할 수 있게 해주는 환경을 구성해줌


//@ComponentScan
// = @component 어노테이션이 있는 클래스들을 찾아서 객체를 생성하고
// 객체 관리 컨테이너에 추가함.(spring IOC 컨테이너)



// spring-boot의 특징 => 자동화된 설정과 의존성 주입을 이용한 개발방법 채택
//		자동화된 설정 = 의존성 라이브러리, 어노테이션 프로세싱
@SpringBootApplication
public class ControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);

	}

}
