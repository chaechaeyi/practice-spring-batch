package com.batch.pt.repository.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Integer> {

    @Query(value = "SELECT new AggregatedStatistics(s.statisticsAt, SUM(s.allCount), SUM(s.attendedCount), SUM(s.cancelledCount)) " +
            "         FROM StatisticsEntity s " +
            "        WHERE s.statisticsAt BETWEEN :from AND :to " +
            "     GROUP BY s.statisticsAt")
    List<AggregatedStatistics> findByStatisticsAtBetweenAndGroupBy(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
