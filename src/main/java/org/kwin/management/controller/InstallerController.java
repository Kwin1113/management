package org.kwin.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.entity.Installer;
import org.kwin.management.form.InstallerForm;
import org.kwin.management.service.InstallerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/installer")
public class InstallerController {

    @Autowired
    InstallerService installerService;

    @ResponseBody
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> model) {
        List<Installer> installerList = installerService.list();
        model.put("installerList", installerList);
        return new ModelAndView("installer/list", model);
    }

    @PostMapping("/add")
    public String add(InstallerForm installerForm) {
        Installer installer = new Installer();
        BeanUtils.copyProperties(installerForm, installer);
        installerService.add(installer);
        return "redirect:/installer/list";
    }

    @ResponseBody
    @GetMapping("/selectOne/{installerId}")
    public ModelMap selectOne(@PathVariable Integer installerId) {
        ModelMap model = new ModelMap();
        Installer installer = installerService.selectOne(installerId);
        model.addAttribute("installer", installer);
        return model;
    }

    @PostMapping("/update")
    public String modify(InstallerForm installerForm) {
        Installer installer = new Installer();
        BeanUtils.copyProperties(installerForm, installer);
        installerService.update(installer);
        return "redirect:/installer/list";
    }

    @GetMapping("/delete/{installerId}")
    public String delete(@PathVariable Integer installerId) {
        installerService.delete(installerId);
        return "redirect:/installer/list";
    }
}
