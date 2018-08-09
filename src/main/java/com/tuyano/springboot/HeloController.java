package com.tuyano.springboot;

import com.tuyano.springboot.repositories.MyDataRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    MyDataService myDataService;

    @Autowired
    MyDataBean myDataBean;

    @PostConstruct
    public void init(){
        log.info("----------------------{}", "PostConstruct Init");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){

        //Iterable<MyData> list = myDataRespository.findAll();
        //Iterable<MyData> list = dao.getAll();
        //Iterable<MyData> list = dao.findByName(name);
        Iterable<MyData> list = myDataService.getAll();

        mav.setViewName("index");
        mav.addObject("list", list);
        mav.addObject("title", "index Page");

        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView indexById(@PathVariable("id") Long id, ModelAndView mav){

        mav.setViewName("pickup");
        mav.addObject("title", "Pickup Page");

        String table = "<table>" + myDataBean.getTableTagById(id) + "</table>";
        mav.addObject("msg", "pickup data id = "+id);
        mav.addObject("data", table);

        return mav;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){

        Iterable<MyData> list = myDataService.getAll();

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
            List<MyData> list = myDataService.find(param);
            mav.addObject("datalist", list);
        }

        return mav;
    }


}
