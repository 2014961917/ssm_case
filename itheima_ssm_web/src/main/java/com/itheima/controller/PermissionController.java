package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
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

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                          @RequestParam(name = "size",required = true,defaultValue = "4")Integer size,
                          Model model) throws Exception {
        List<Permission> roles = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        model.addAttribute("pageInfo",pageInfo);
        return "permission-list";
    }

    @RequestMapping("save")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:findAll";
    }
}
