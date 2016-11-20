package com.programcreek.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Domain.Asset;

@Controller
public class AssetController {
	
	@RequestMapping(value="/asset", method=RequestMethod.GET)
	public ModelAndView createAsset()
	{
		ModelAndView mv = new ModelAndView("asset");
		return mv;
	}
	
	@RequestMapping(value="/asset", method=RequestMethod.POST)
	public ModelAndView generateAsset(@ModelAttribute("SpringWeb")Asset asset)
	{
		ModelAndView mv = new ModelAndView("asset");
		mv.addObject("message", "post");
		
		return mv;
	}
	
}
