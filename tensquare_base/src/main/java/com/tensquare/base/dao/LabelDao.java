package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2019-02-12 22:54
 **/
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {

}