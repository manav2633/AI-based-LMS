package com.exam.portal.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.portal.Model.AdminUser;
import com.exam.portal.Model.modules;
import com.exam.portal.Repository.AdminUserRepo;
import com.exam.portal.Repository.moduleRepository;
import com.exam.portal.Repository.topicRepository;

@Controller
public class moduleController {

	@Autowired
	moduleRepository repo;

	@Autowired
	topicRepository topicrepo;

	@Autowired
	AdminUserRepo adminuserrepo;;
	
	
	@GetMapping("/organiser/module")
    public String showModules(Model model ){
	
		List<modules> m = repo.findAll();

		model.addAttribute("m",m);
		
		
		 return "organiser/organization/mview";
    }
	
	@GetMapping("/organiser/createModule")
	public String createModules(Model model, modules module){
		model.addAttribute("module", new modules());
		model.addAttribute("topiclist", topicrepo.findAll());
		return "organiser/organization/module";
	}

	@PostMapping("/registerModule")
	public String RegisterModule(@ModelAttribute("module") modules module,Model Model, Principal principal){
		AdminUser creator=adminuserrepo.findByEmail(principal.getName());
		module.setCreatorId(creator.getId());
		module.setCreateddate(new Date());
		
		
		repo.save(module);
		return "redirect:/organiser/module";
	}

}