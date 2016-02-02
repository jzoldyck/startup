package com.olarn.framework.web;
import com.olarn.framework.entity.AppPrivilege;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/appprivileges")
@Controller
@RooWebScaffold(path = "appprivileges", formBackingObject = AppPrivilege.class)
public class AppPrivilegeController {
}
