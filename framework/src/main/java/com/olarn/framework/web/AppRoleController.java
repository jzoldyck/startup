package com.olarn.framework.web;
import com.olarn.framework.entity.AppRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/approles")
@Controller
@RooWebScaffold(path = "approles", formBackingObject = AppRole.class)
public class AppRoleController {
}
