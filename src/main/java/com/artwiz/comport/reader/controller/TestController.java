package com.artwiz.comport.reader.controller;

import com.artwiz.comport.reader.service.ComportService;
import com.artwiz.comport.reader.util.HttpRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private ComportService comportService;

    @GetMapping(value="/", produces = "text/html")
    public ModelAndView display(Model model) {
        return new ModelAndView("/view/home.jsp");
    }

    @GetMapping(value = "/addNewReader", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView addNewReader(@RequestParam(value = "baud") long baud) {
        ModelAndView modelAndView = new ModelAndView(HttpRequestUtils.getDynamicPageLocation("home"));
        modelAndView.addObject("baudValue", comportService.init(comportService.init(baud)));
        comportService.init(baud);
        return modelAndView;
    }

    @GetMapping(value = "/getReaderValue", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getValue(@RequestParam(value = "baud") long baud) {
        ModelAndView modelAndView = new ModelAndView(HttpRequestUtils.getDynamicPageLocation("home"));
        modelAndView.addObject("value", comportService.getValue(baud));
        return modelAndView;
    }

}
