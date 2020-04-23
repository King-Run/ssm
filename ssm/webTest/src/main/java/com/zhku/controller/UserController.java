package com.zhku.controller;

import com.github.pagehelper.PageInfo;
import com.zhku.pojo.Role;
import com.zhku.pojo.UserInfo;
import com.zhku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    没有分页的查询
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        System.out.println("控制层：查询所有的用户");
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;
    }

     */

    /**
     * 分页查询所有用户
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        System.out.println("控制层：分页查询所有的用户");
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Model model,UserInfo userInfo){
        System.out.println("控制层：新增的用户");
        userService.save(userInfo);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id){
        System.out.println("控制层：根据id查询用户信息");
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }


    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) int id){
        System.out.println("控制层：根据id查询用户信息和他可以添加的角色");
        ModelAndView mv = new ModelAndView();
//        查询这恶鬼用户的所有信息
        UserInfo userInfo = userService.findById(id);
//        查询这个用户可以添加的角色
        List<Role> otherroles = userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherroles);
        mv.setViewName("user-role-add");
        return mv;
    }



    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) int userId, @RequestParam(name ="ids",required = true) String roleIds[]){
        System.out.println("控制层：根据用户id和角色id给用户添加角色");

        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }

}
