package com.arthur.springdatajpastartup.repository.impl;

import com.arthur.springdatajpastartup.repository.CreditLineCustomRepository;
import org.springframework.stereotype.Repository;


@Repository
public class CreditLineCustomRepositoryImpl implements CreditLineCustomRepository {

    @Override
    public Integer calculateSignInPoint(Long userId, String signDttmStr) {

        if ("2018-08-01".equals(signDttmStr)){
            return 0;
        }

        if ("2018-08-07".equals(signDttmStr)){
            return 5;
        }

        if ("2018-08-14".equals(signDttmStr)){
            return 4;
        }

        return 1;
    }
}
