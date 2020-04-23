package com.zhku.controller;

import com.github.pagehelper.PageInfo;
import com.zhku.pojo.Permission;
import com.zhku.pojo.Role;
import com.zhku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model,@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        System.out.println("控制层：分页查询所有角色");
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        model.addAttribute("pageInfo",pageInfo);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(Role role){
        System.out.println("控制层：新增角色");
        roleService.save(role);
        return "redirect:findAll";
    }


    @RequestMapping("/findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) int id,Model model){
        System.out.println("控制层：给角色添加权限");
//        根据角色id查询角色信息
        Role role = roleService.findById(id);
//        根据角色id查询未添加的权限
        List<Permission> permissions = roleService.findOtherPermission(id);
        model.addAttribute("role",role);
        model.addAttribute("permissionList",permissions);
        return "role-permission-add";
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) int id,Model model,@RequestParam(name = "ids",required = true) String[] permissionIds){
        System.out.println("控制层：保存给角色添加权限");
//        根据角色id赋予权限
        roleService.addPermissionToRole(id,permissionIds);

        return "redirect:findAll";
    }

}
