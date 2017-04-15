package org.fkit.hrm.controller;

import org.fkit.hrm.domain.Job;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class JobController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex, @ModelAttribute Job job){
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询用户信息
        List<Job> jobs = hrmService.findJob(job, pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "job/job";
    }

    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids, ModelAndView mv){
        //分解ids
        String[] idArray = ids.split(",");
        for(String id : idArray){
            hrmService.removeJobById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/job/selectJob");
        return mv;
    }

    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(String flag,@ModelAttribute Job job, ModelAndView mv){
        if(flag.equals("1")){
            mv.setViewName("job/showAddJob");
        }else{
            hrmService.addJob(job);
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }

    //处理修改职位请求
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateDept(String flag, @ModelAttribute Job job, ModelAndView mv){
        if(flag.equals("1")){
            Job target = hrmService.findJobById(job.getId());
            mv.addObject("job",target);
            mv.setViewName("job/showUpdateJob");
        }else{
            hrmService.modifyJob(job);
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }




}
