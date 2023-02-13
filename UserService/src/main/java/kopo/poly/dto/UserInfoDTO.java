package kopo.poly.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

    private String userId;    //아이디
    private String nickName;  //닉네임
    private String password;  //비밀번호
    private String email;     //이메일
    private String addr1;     //주소1
    private String addr2;     //상세주소
    private String regId;     //작성자
    private String regDt;     //작성일자
    private String chgId;     //수정자
    private String chgDt;     //수정일자

}
