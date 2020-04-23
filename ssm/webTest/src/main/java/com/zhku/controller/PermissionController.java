package com.zhku.controller;

import com.github.pagehelper.PageInfo;
import com.zhku.pojo.Permission;
import com.zhku.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 分页查询所有权限
     * @param model
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(name = "page",required = true,defaultValue = "1") int page, @RequestParam(name = "size",required = true,defaultValue = "4")int size){
        System.out.println("控制层：分页查询所有权限");
        List<Permission> permissions = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissions);
        model.addAttribute("pageInfo",pageInfo);
        return "permission-list";

    }

    @RequestMapping("/save")
    public String save(Permission permission){
        System.out.println("控制层：新增资源权限");
        permissionService.save(permission);
        return "redirect:findAll";

    }

}
