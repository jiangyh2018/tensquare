package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    public Label save(Label label) {
        label.setId(idWorker.nextId() + "");
        return labelDao.save(label);
    }

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }

    /**
     * 根据某个或某几个条件查询
     * @param label
     * @return
     */
    public List<Label> search(Label label) {
        Specification<Label> labelSpecification = getLabelSpecification(label);
        return labelDao.findAll(labelSpecification);
    }

    /**
     * 获取查询条件
     * @param label
     * @return
     */
    private Specification<Label> getLabelSpecification(Label label) {
        if(label==null){
            return null;
        }
        return new Specification<Label>() {
                List<Predicate> predicateList = new ArrayList<>();
                @Nullable
                @Override
                public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                        Predicate labelName = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                        predicateList.add(labelName);
                    }
                    if (label.getState() != null && !"".equals(label.getState())) {
                        Predicate state = criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                        predicateList.add(state);
                    }
                    if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                        Predicate recommend = criteriaBuilder.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%");
                        predicateList.add(recommend);
                    }

                    Predicate[] predicates = new Predicate[predicateList.size()];
                    predicates = predicateList.toArray(predicates);
                    return criteriaBuilder.and(predicates);
                }
            };
    }

    /**
     * 分页查询
     * @param page 当前页码
     * @param size  每页展示数量
     * @param labe  查询条件
     * @return  List<Label>
     */
    public Page<Label> searchByPage(int page, int size, Label labe) {
        Specification<Label> labelSpecification=getLabelSpecification(labe);
//        Pageable pageable=new PageRequest(page,size);
        Pageable pageable=PageRequest.of(page-1,size);

        Page<Label> labelPage = labelDao.findAll(labelSpecification, pageable);
        return labelPage;
    }
}