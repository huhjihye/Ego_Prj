package kopo.poly.controller;

import kopo.poly.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Slf4j
@RequestMapping(value="/user")
@Controller
public class UserInfoController {

    //사용되는 서비스를 메모리에 적재 (싱글톤 패턴)
    @Resource(name="UserInfoService")
    private IUserInfoService userInfoService;

    //회원가입 화면
    @GetMapping(value="userRregForm")
    public String userRregForm(){
        log.info(this.getClass().getName()+"user/userRegForm 시작");

        return "/user/UserRegForm";
    }

}
