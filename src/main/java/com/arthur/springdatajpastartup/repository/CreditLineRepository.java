package com.arthur.springdatajpastartup.repository;

import java.util.List;

import com.arthur.springdatajpastartup.entity.CreditLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CreditLineRepository extends JpaRepository<CreditLine, Long>,
        CreditLineCustomRepository {

    List<CreditLine> findByUserIdAndChangeType(Long userId, String changeType);

    Page<CreditLine> findByUserIdAndChangeTypeOrderByChangeOnDttmAsc(Long userId, String changeType, Pageable pageable);

    @Query(value = "SELECT SUM(l.creditPoint) FROM CreditLine l where l.userId = :userId and changeType = :changeType and changeOnDtStr = :changeOnDtStr ")
	Long sumUserPointsOfSpeicalDayByChangeType(@Param("userId") Long userId, @Param("changeType") String changeType,
                                               @Param("changeOnDtStr") String changeOnDtStr);

	@Query("SELECT L FROM CreditLine L WHERE L.userId = :userId and L.changeType = :changeType ORDER BY L.changeOnDttm ASC")
	Page<CreditLine> pageByUserIdAndChangeType(@Param("userId")Long userId, @Param("changeType")String changeType, Pageable pageable);

    @Query(value = "SELECT * FROM APP_CREDIT_CHG_LN L WHERE L.USER_ID = :userId and L.CHANGE_TYPE = :changeType ORDER BY L.CHANGE_ON_DTTM ASC",
    nativeQuery = true)
    Page<CreditLine> pageByUserIdAndChangeTypeNativeSql(@Param("userId")Long userId, @Param("changeType")String changeType, Pageable pageable);


}
