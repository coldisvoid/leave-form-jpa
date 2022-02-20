package com.nhooo.demo.service;

import com.nhooo.demo.aop.MyTransactional;
import com.nhooo.demo.model.HistoryRecord;
import com.nhooo.demo.model.UserRecord;
import com.nhooo.demo.repository.HistoryRepository;
import com.nhooo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HistoryService
{
    @Autowired
    private HistoryRepository historyRepository;
    //获取所有
    public List<HistoryRecord> getAllUsers()
    {
        List<HistoryRecord>historyRecords = new ArrayList<>();

        historyRepository.findAll(Sort.by(Sort.Direction.ASC,"hid")).forEach(historyRecords::add);
        return historyRecords;
    }
    //增加
    public void addUser(HistoryRecord historyRecord)
    {
        historyRepository.save(historyRecord);
    }
    //



}