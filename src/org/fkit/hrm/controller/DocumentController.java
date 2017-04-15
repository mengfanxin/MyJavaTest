package org.fkit.hrm.controller;

import com.sun.javafx.image.IntPixelGetter;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class DocumentController {

    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    //处理select
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(
            Model model, Integer pageIndex,
            @ModelAttribute Document document
            ){
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        List<Document> documents = hrmService.findDocument(document,pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("pageModel",pageModel);
        return "document/documet";
    }

    //处理添加请求
    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(
            String flag,
            @ModelAttribute Document document,
            ModelAndView mv,
            HttpSession session
    ) throws Exception{
        if (flag.equals("1")){
            mv.setViewName("document/showAddDocument");
        }else{
            //上传文件路径
            String path = session.getServletContext().getRealPath("/upload");
            //上传文件名
            String fileName = document.getFile().getOriginalFilename();
            //
        }
    }




}
