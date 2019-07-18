package com.jason.controller;

import com.github.pagehelper.PageInfo;
import com.jason.bean.UserInfo;
import com.jason.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")

public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userInfoService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }



    @RequestMapping("delete.do")
    public String delete(int id) {
        userInfoService.delete(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("add.do")
    public String add(UserInfo userinfo) {
        userInfoService.add(userinfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("toUpdate.do")
    public ModelAndView toUpdate(int id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userinfo = userInfoService.selectById(id);
        mv.addObject("userInfo", userinfo);
        mv.setViewName("user-update");
        return mv;
    }

    @RequestMapping("update.do")
    public String update(UserInfo userInfo) {
        userInfoService.update(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("deleteAll.do")
    @ResponseBody
    public String deleteAll(String userList){
        String[] strs=userList.split(",");
        List<Integer> ids=new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            ids.add(Integer.parseInt(strs[i]));
        }
        userInfoService.deleteAll(ids);
        return "";
    }
}
