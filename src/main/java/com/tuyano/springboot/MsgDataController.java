package com.tuyano.springboot;

import com.tuyano.springboot.repositories.MsgDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MsgDataController {

    @Autowired
    MsgDataRepository msgDataRepository;

    @Autowired
    MsgDataDaoImpl dao;

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public ModelAndView msg(ModelAndView mav){
        mav.setViewName("showMsgData");
        mav.addObject("title", "Sample");
        mav.addObject("msg", "MsgData의 예제입니다.");

        MsgData msgData = new MsgData();
        mav.addObject("formModel", msgData);
        List<MsgData> list = dao.getAll();
        mav.addObject("datalist", list);

        return mav;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public ModelAndView msgform(
            @Valid @ModelAttribute MsgData msgData,
            Errors result,
            ModelAndView mav){

        if(result.hasErrors()){
            mav.setViewName("showMsgData");
            mav.addObject("title", "Sample Error");
            mav.addObject("msg", "값을 다시 확인해주세요");
            return mav;
        }else{
            msgDataRepository.save(msgData);
            return new ModelAndView("redirect:/msg");
        }
    }
}
