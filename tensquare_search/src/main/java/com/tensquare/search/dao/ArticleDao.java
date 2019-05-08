package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @author:
 * @create: 2019-03-31 21:55
 **/
public interface ArticleDao extends ElasticsearchRepository<Article, String> {

}