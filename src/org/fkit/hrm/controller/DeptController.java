package org.fkit.hrm.controller;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class DeptController {

    /**
     * 自动注入
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex, @ModelAttribute Dept dept){

        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Dept> depts = hrmService.findDept(dept,pageModel);
        model.addAttribute("dept",dept);
        model.addAttribute("pageModel",pageModel);
        return "dept/dept";
    }

//    删除部门
    @RequestMapping(value = "/dept/removeDept")
    public ModelAndView removeDept(String ids, ModelAndView mv){
        //分解ids
        String[] idArray = ids.split(",");
        for (String id : idArray){
            hrmService.removeDeptById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/dept/selectDept");
        return mv;

    }


    //处理添加请求
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(
            String flag,
            @ModelAttribute Dept dept,
            ModelAndView mv
    ){
        if(flag.equals("1")){
            mv.setViewName("dept/showAddDept");
        }else{
            hrmService.addDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }

    //处理部门修改请求
    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(
            String flag,
            @ModelAttribute Dept dept,
            ModelAndView mv
    ){
        if (flag.equals("1")){
            Dept target = hrmService.findDeptById(dept.getId());
            mv.addObject("dept",dept);
            mv.setViewName("dept/showUpdateDept");
        }else{
            hrmService.modifyDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }



}
