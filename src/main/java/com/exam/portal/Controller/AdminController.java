package com.exam.portal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.portal.Repository.OrganizationRepository;


@Controller

public class AdminController {

    @Autowired
    OrganizationRepository orgrepo;

    @GetMapping("admindashboard")
    public String showadminDashboard(Model model) {
		System.out.println("admin dashboard");
        	int examcount=0;
		Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*if(user instanceof OrganiserDetails){
			Organiser org = ((OrganiserDetails) user).getOrg();
			      
			examcount = examRepository.findByOrganiserId(org.getId()).size();
		}*/
		long organizationCount = orgrepo.count();
		// Add the organization count to the model
        model.addAttribute("organizationCount", organizationCount);
		model.addAttribute("examcount",examcount);
		return "organiser/dashboard";
    }
    
	@GetMapping("organizationdashboard")
    public String showOrgDashboard(Model model) {
		System.out.println("organization dashboard");
      	return "organization_dashboard";
    }



}
