package com.tuyano.springboot;

import com.tuyano.springboot.repositories.MyDataRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class HeloController {

    @Autowired
    MyDataRespository myDataRespository;

    @Autowired
    MyDataDaoImpl dao;

    @PostConstruct
    public void init(){
        log.info("----------------------{}", "PostConstruct Init");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam String name,  ModelAndView mav){

        //Iterable<MyData> list = myDataRespository.findAll();
        //Iterable<MyData> list = dao.getAll();
        Iterable<MyData> list = dao.findByName(name);

        mav.addObject("list", list);
        mav.addObject("title", "index Page");
        mav.setViewName("index");

        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){

        Iterable<MyData> list = dao.getAll();

        mav.setViewName("find");
        mav.addObject("value", "");
        mav.addObject("datalist", list);

        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav){

        mav.setViewName("find");

        String param = request.getParameter("fstr");

        if(param == ""){
            mav = new ModelAndView("redirect:/find");
        }else{
            mav.addObject("value", param);
            List<MyData> list = dao.find(param);
            mav.addObject("datalist", list);
        }

        return mav;
    }


}
