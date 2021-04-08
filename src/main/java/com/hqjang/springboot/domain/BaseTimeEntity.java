package com.hqjang.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;

@Getter
@MappedSuperclass //jpa entity 클래스들이 이 클래스를 상속받을 경우, 필드(createdData, lastModifiedData)도 칼럼으로 인식하도록.
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 포함시키기
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
