package com.nhooo.demo.repository;

import com.nhooo.demo.model.HistoryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<HistoryRecord, Integer>
{
    Page<HistoryRecord> findByName(String name, Pageable pageable);
    Page<HistoryRecord> findByLeaveType(String leaveType, Pageable pageable);
    Page<HistoryRecord> findByNameOrLeaveType(String name, String leaveType,Pageable pageable);
    Page<HistoryRecord> findByNameAndLeaveType(String name, String leaveType,Pageable pageable);

}