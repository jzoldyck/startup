package com.olarn.framework.web;
import com.olarn.framework.entity.OlarnObject;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/olarnobjects")
@Controller
@RooWebScaffold(path = "olarnobjects", formBackingObject = OlarnObject.class)
public class OlarnObjectController {
}
