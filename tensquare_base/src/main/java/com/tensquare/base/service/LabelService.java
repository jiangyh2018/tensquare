package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2019-02-12 22:55
 **/
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public Label save(Label label){
        label.setId(idWorker.nextId()+"");
       return labelDao.save(label);
    }

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String labelId){
        return labelDao.findById(labelId).get();
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String labelId){
        labelDao.deleteById(labelId);
    }

}