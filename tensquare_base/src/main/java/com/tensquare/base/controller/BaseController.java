package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author:
 * @create: 2019-02-12 22:42
 **/
@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class BaseController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private HttpServletRequest request;

    @Value("${sms.ip}")
    private String ip;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        System.out.println("333");
        System.out.println(ip);
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId) {
        String header = request.getHeader("Authorization");
        System.out.println("---"+header);
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据某个或某几个条件查询
     *
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(@RequestBody(required = false) Label label) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.search(label));
    }

    /**
     * 分页查询
     *
     * @param page 当前页码
     * @param size 每页展示数量
     * @param labe 查询条件
     * @return List<Label>
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size, @RequestBody(required = false) Label labe) {
        Page<Label> labelPage = labelService.searchByPage(page, size, labe);
        long totalElements = labelPage.getTotalElements();
        List<Label> content = labelPage.getContent();
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(totalElements, content));
    }

}