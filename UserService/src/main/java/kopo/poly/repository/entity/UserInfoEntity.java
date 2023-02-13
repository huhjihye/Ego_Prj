package kopo.poly.repository.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor   //기본 생성자를 생성해줌
@AllArgsConstructor   //전체 변수를 생성하는 생성자를 만들어줌
@Table(name="USER_INFO")   //테이블 이름
@DynamicInsert       //insert 구문 생성 시점에 null이 아닌 컬럼들만 포함
@DynamicUpdate       //update 구문 생성 시점에 null이 아닌 컬럼만 포함
@Builder       //JPA 엔티티 객체들에 사용 , 1. 인자가 많을 경우 쉽고 안전하게 객체 생성가능 2. 인자의 순서와 상관없이 객체 생성이 가능  3. 적절한 책임을 부여하뎌 가독성 높일 수 있음
@Entity

public class UserInfoEntity {
    @Id
    @Column(name="User_ID")
    private String userId;

    @NonNull
    @Column(name="NICK_NAME")
    private String nickName;

    @NonNull
    @Column(name="PASSWORD")
    private String password;

    @NonNull
    @Column(name="EMAIL")
    private String email;

    @NonNull
    @Column(name="ADDR1")
    private String addr1;

    @NonNull
    @Column(name="ADDR2")
    private String addr2;

    @Column(name="REG_ID")
    private String regId;

    @Column(name="REG_DT")
    private String regDt;

    @Column(name="CHG_ID")
    private String chgId;

    @Column(name="CHG_DT")
    private String chgDt;

}
