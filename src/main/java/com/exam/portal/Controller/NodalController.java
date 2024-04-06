package com.exam.portal.Controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.portal.Model.NodalOfficer;
import com.exam.portal.Model.Organization;
import com.exam.portal.Repository.NodalOfficerRepository;
import com.exam.portal.Repository.OrganiserRepository;
import com.exam.portal.Repository.OrganizationRepository;

@Controller
public class NodalController {

	@Autowired
	NodalOfficerRepository nRepository;

	@Autowired
	OrganizationRepository orgrepo;

	@GetMapping("/organiser/nodal")
	public String viewNodal(Model model) {
		List<NodalOfficer> nodal = nRepository.findAll();
		model.addAttribute("nodal", nodal);
		return "organiser/nodal/nodal_list";
	}

	@GetMapping("/nodal-sign-up.html")
	public String NodalSignup() {
		return "nodal-sign-up.html";
	}

	@GetMapping("/organiser/nodalCreate")
	public String createNodal() {
		return "organiser/nodal/nodal_create";
	}

	@PostMapping("/organiser/nodal/register")
	public String registerNodal(@ModelAttribute NodalOfficer nodelOfficer, Principal principal) {
		// Save the organization to the database
		Organization organization=orgrepo.findByEmail_Id(principal.getName());
		nodelOfficer.setOrganizationid(organization.getOrganizationId());
		nodelOfficer.setRegistereddate(new Date());
		nRepository.save(nodelOfficer);
		// Redirect to a success page or return a success message
		return "redirect:/organiser/nodal";
	}


	@GetMapping("/organiser/nodal/view/{id}")
	public String viewNodal(@PathVariable("id") Integer id, Model model) {
		// Use Optional's get() method to extract the NodalOfficer entity from the Optional
		Optional<NodalOfficer> optionalNodal = nRepository.findById(id);
		
		if (optionalNodal.isPresent()) {
			NodalOfficer nodal = optionalNodal.get();
			model.addAttribute("nodal", nodal);
		} else {
			System.out.println("Nodal officer with ID " + id + " not found");
		}
		return "organiser/nodal/nodal_view";
	}
	
	// Delete item from Config Campaign
	// @RequestMapping(value = "/organiser/deletenodal", method = RequestMethod.GET)
	// public String deleteConfig(@RequestParam("nid") Integer[] Id) {
		
	// 	nRepository.deleteNodal(Arrays.asList(Id));

	// 	return "redirect:/organiser/organization";
	// }


}
