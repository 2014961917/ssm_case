package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @param
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                          @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        //调用service层方法
        List<UserInfo> userInfos =  userService.findAll(page,size);

        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(userInfos);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(UserInfo userInfo) throws Exception {
        ///调用service层的添加用户方法
        userService.saveUser(userInfo);

        return "redirect:/user/findAll";
    }

    @RequestMapping("/deleteUserByIds")
    public String deleteUserByIds(String[] ids){
        System.out.println(ids);
        for (String id : ids) {
            userService.deleteUserById(id);
        }
        return "redirect:findAll";
    }

    @RequestMapping("/findUserById")
    public String findUserById(String id,Model model){
        UserInfo user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "user-show";
    }

    @RequestMapping("/editUser")
    public String editUser(String id,Model model){
        UserInfo userInfo = userService.findUserById(id);
        model.addAttribute("user",userInfo);
        return "user-update";
    }

    /**
     * 更新用户信息（包含状态密码，以及其他信息）
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping("updateUser")
    public String updateUser(UserInfo userInfo,Model model){
        userService.updateUser(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id,Model model){
        UserInfo userInfo = userService.findUserByIdAndAllRole(id);
        model.addAttribute("userInfo",userInfo);
        return "user-role-add";
    }

    @RequestMapping("addRoleToUser")
    public String addRoleToUser(){

        return "user-role-add";
    }
}
