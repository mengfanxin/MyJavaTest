package org.fkit.hrm.controller;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class UserController {

    /**
     * 自动注入service
     */

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;
    //登录
    @RequestMapping(value="login")
    public ModelAndView login(
        @RequestParam("loginname") String loginname,
        @RequestParam("password") String password,
        HttpSession session,
        ModelAndView mv
    ){
        User user = hrmService.login(loginname, password);
        if (user != null){
            //将用户保存到httpSession中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            mv.setViewName("redirect:/main");
        }else{
            mv.addObject("message","登录名或密码错误!请重新输入");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }
    //处理查询请求
    @RequestMapping(value="/user/selectUser")
    public String selectUser(
            Integer pageIndex,
            @ModelAttribute User user,
            Model model
                             ){
        System.out.println("user = "+user );
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询用户信息
        List<User> users = hrmService.findUser(user, pageModel);
        model.addAttribute("user", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
    }

    //处理删除用户请求
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView mv){
        //分解ID字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            hrmService.removeUserById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/user/selectUser");
        return mv;
    }

    //处理用户请求
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag,@ModelAttribute User user, ModelAndView mv){
        if(flag.equals("1")){
            //根据ID查询用户
            User target = hrmService.findUserById(user.getId());
            //设置 model数据
            mv.addObject("user",target);
            //返回修改员工页面
            mv.setViewName("user/showUpdateUser");
        }else{
            //执行修改操作
            hrmService.modifyUser(user);
            //设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }
    //处理添加请求
    @RequestMapping(value = "user/addUser")
    public ModelAndView addUser(
            String flag,
            @ModelAttribute User user,
            ModelAndView mv
    ){
        if(flag.equals("1")){
            //设置跳转到添加页面
            mv.setViewName("/user/showAddUser");
        }else{
            //执行添加操作
            hrmService.addUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }





}
