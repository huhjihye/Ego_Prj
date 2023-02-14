package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.repository.UserInfoRepository;
import kopo.poly.repository.entity.UserInfoEntity;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor    //생성자를 자동 생성
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

    //@RequiredArgsConstructor를 통해 생성자를 자동 생성
    //userInforRepository 변수에 이미 메모리에 올라간 UserInfoRepository 객체를 넣어줌
    //예전에는 autowired 어노테이션을 통해 설정했었지만, 이제는 생성자를 통해 객체 주입함\
    //즉, JPA Repository를 불러올 수 있게
     private final UserInfoRepository userInfoRepository;




     //회원가입
    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
        //회원가입 성공 : 1, 아이디 중복으로 인한 가입 취소 :2, 기타 에러발생 : 0
        int res= 0;

        //정보 받기
        String userId= CmmUtil.nvl(pDTO.getUserId());     //아이디
        String nickName= CmmUtil.nvl(pDTO.getNickName()); //닉네임
        String password= CmmUtil.nvl(pDTO.getPassword()); //비밀번호
        String email= CmmUtil.nvl(pDTO.getEmail());       //이메일
        String addr1= CmmUtil.nvl(pDTO.getAddr1());       //주소
        String addr2= CmmUtil.nvl(pDTO.getAddr2());       //주소2

        //잘 받아왔는지 로그 찍기
        log.info("userId : " +userId);
        log.info("nickName : " +nickName);
        log.info("password : " +password);
        log.info("email : " +email);
        log.info("addr1 : " +addr1);
        log.info("addr2 : " +addr2);

        //잘 받아왔으니까 이제 중복 검사를 해야겠죠?

        //1. 회원가입 중복 방지를 위해 DB에서 ID기준으로 중복검사
        //Optional 이란?
        // - 'null일 수도 있는 객체'를 감싸는 일종의 Wrapper 클래스 , 반복적인 null체크를 줄일 수 있음
        // -  Spring Data JPA 사용시 Repository에서 리턴 타입을 Optional 바로 받을 수 있도록 지원
        // -  Repository에서 Optional을 반환하는 경우 , 원하는 값이 있으면 원하는 객체로 받고 없으면 Exception 처리 하는 패턴
        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(userId);

        //값이 존재한다면?  : 이미 회원가입 되어있음
        if(rEntity.isPresent()){
            res=2;
        }else{
            //값이 없다면?  : 회원가입 진행  -> Builder로 Entity 생성
            UserInfoEntity pEntity = UserInfoEntity.builder()
                    .userId(userId).nickName(nickName).password(password)
                    .email(email).addr1(addr1).addr2(addr2)
                    .build();

            //DB에 저장
            userInfoRepository.save(pEntity);


            //회원가입 여부를 위해 한 번 더 조회
            //회원가입 후 저장 안 될 수도 있기 때문에 조회 한번 더
            rEntity= userInfoRepository.findByUserId(userId);

            if(rEntity.isPresent()){    //값이 존재하면 : 회원가입 성공
                res=1;
            }else{                     // 값이 없다면 : 회원가입 실패
                res=0;
            }
        }
        return res;
    }
}
