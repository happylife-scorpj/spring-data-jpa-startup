package com.arthur.springdatajpastartup.repository;

public interface CreditLineCustomRepository {

    Integer calculateSignInPoint(Long userId, String signDttmStr);
}
