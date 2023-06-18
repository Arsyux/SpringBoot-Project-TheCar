package com.arsyux.thecar.domain;

// 회원이 가질 수 있는 권한을 제한하기 위해 열거형을 이용하여 RoleType을 추가함.
// 이렇게 열거형을 사용하면 기존 권한의 변경이나 새로운 권한의 추가를 쉽게 할 수 있음.
public enum RoleType {
	USER, ADMIN;
}
