package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
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

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                          @RequestParam(name = "size",required = true,defaultValue = "4")Integer size,
                          Model model) throws Exception {
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        model.addAttribute("pageInfo",pageInfo);
        return "role-list";
    }

    @RequestMapping("findById")
    public String findById(String id ,Model model){
        Role role = roleService.findById(id);
        model.addAttribute("role",role);
        return "role-show";
    }

    @RequestMapping("save")
    public String save(Role role, Model model){
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("delRole")
    public String delRole(String[] ids, Model model){
        for (String id : ids) {
            roleService.delRole(id);
        }
        return "redirect:findAll";
    }

    @RequestMapping("edit")
    public String edit(String id, Model model){
        Role role = roleService.findById(id);
        model.addAttribute("role",role);
        return "role-update";
    }

    @RequestMapping("update")
    public String update(Role role, Model model){
        roleService.update(role);
        return "redirect:findAll";
    }
}
