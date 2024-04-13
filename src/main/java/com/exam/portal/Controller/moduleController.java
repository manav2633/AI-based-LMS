package com.exam.portal.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.portal.Model.AdminUser;
import com.exam.portal.Model.Course;
import com.exam.portal.Model.Topic;
import com.exam.portal.Model.modules;
import com.exam.portal.Repository.AdminUserRepo;
import com.exam.portal.Repository.CourseRepository;
import com.exam.portal.Repository.moduleRepository;
import com.exam.portal.Repository.topicRepository;

@Controller
public class moduleController {

	@Autowired
	moduleRepository repo;

	@Autowired
	topicRepository topicrepo;

	@Autowired
	AdminUserRepo adminuserrepo;
	
	@Autowired
	CourseRepository courseRepository;
	
	
	@GetMapping("/organiser/module")
    public String showModules(Model model ){
	
		List<modules> m = repo.findAll();

		model.addAttribute("m",m);
		
		
		 return "organiser/module/mview";
    }
	
	@GetMapping("/organiser/createModule")
	public String createModules(Model model, modules module){
		
		model.addAttribute("course", courseRepository.findAll());
		model.addAttribute("module", new modules());
		model.addAttribute("topiclist", topicrepo.findAll());
		return "organiser/module/module";
	}

	@PostMapping("/registerModule")
	public String RegisterModule(@ModelAttribute("module") modules module,Model Model, Principal principal){
		AdminUser creator=adminuserrepo.findByEmail(principal.getName());
		module.setCreatorId(creator.getId());
		module.setCreateddate(new Date());
		
		
		repo.save(module);
		return "redirect:/organiser/module";
	}

	@GetMapping("/organiser/module/edit")
    public String editModule(@RequestParam(name = "id") Integer id, Model model) {
        Optional<modules> module = repo.findByModuleId(id);
		
        if (module.isPresent()) {
            modules modules = module.get();
			
            
            model.addAttribute("modules", modules);
			model.addAttribute("topicList", topicrepo.findAll());
            return "organiser/module/edit_module"; // Assuming this is your edit page
        } else {
            // Handle program not found case
            return "redirect:/organiser/module"; // Assuming you have a Thymeleaf template named programNotFound.html
        }
	
}


@PostMapping("/organiser/module/edit")
public String saveEditedCourse(@ModelAttribute("modules") modules module,
        @RequestParam("selectedTopic") List<Integer> selectedTopic,
        Principal principal) {
		

		Optional<modules> optionalModule = repo.findByModuleId(module.getModuleId());
		if (optionalModule.isPresent()) {
			// AdminUser adminuser = adminUserRepo.findByEmail(principal.getName());
			modules existingModule = optionalModule.get();
			existingModule.setModuleName(module.getModuleName());
			existingModule.setWeightage(module.getWeightage());
			existingModule.setModuleDescription(module.getModuleDescription());
			existingModule.setCreateddate(new Date());

			// Update the courses for the program
			List<Topic> updatedTopics = topicrepo.findAllById(selectedTopic);
			existingModule.setTopics(updatedTopics);

			repo.save(existingModule);
		} else {
			// Handle the case where the program with the given ID does not exist
			// Redirect or show an error message
			return "redirect:/organiser/dashboard";
		}

		// Redirect to a confirmation page or another appropriate page
		return "redirect:/organiser/module";
	}


}