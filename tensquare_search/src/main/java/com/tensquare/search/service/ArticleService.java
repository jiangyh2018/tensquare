package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:
 * @create: 2019-03-31 21:56
 **/
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public void save(Article article){
        articleDao.save(article);
    }

}