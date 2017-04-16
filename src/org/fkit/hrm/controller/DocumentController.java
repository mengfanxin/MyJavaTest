package org.fkit.hrm.controller;

import com.mchange.io.FileUtils;
import com.sun.javafx.image.IntPixelGetter;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
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
            //将上传文件保存到一个目标文件当中
            document.getFile().transferTo(new File(path+File.separator+fileName));
            //设置filename
            document.setFileName(fileName);
            //设置关联的USER对象
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);
            hrmService.addDocument(document);
            mv.setViewName("document/selectDocument");
        }
        return mv;
    }


    //处理删除文档请求
    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(
            String flag,
            @ModelAttribute Document document,
            ModelAndView mv
    ){
        if (flag.equals("1")){
            Document target = hrmService.findDocumentById(document.getId());
            mv.addObject("document",target);
            mv.setViewName("document/showUpdateDocument");
        }else{
            hrmService.modifyDocument(document);
            mv.setViewName("redirect:/document/selectDocument");
        }
        return mv;
    }


//    下载请求
    @RequestMapping(value = "/document/download")
    public ResponseEntity<byte[]> down(Integer id, HttpSession session) throws Exception{
        Document target = hrmService.findDocumentById(id);
        String fileName = target.getFileName();
        String path = session.getServletContext().getRealPath("/upload");
        File file = new File(path+File.separator+fileName);
        HttpHeaders headers = new HttpHeaders();

        String downFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attchment", downFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);


    }

}
