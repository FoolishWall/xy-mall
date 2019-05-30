package com.njupt.xymall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页展示Controller
 * @author ZXL
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
}
