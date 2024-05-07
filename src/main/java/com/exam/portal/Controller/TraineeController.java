package com.exam.portal.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.exam.portal.Model.AdminUser;
import com.exam.portal.Model.trainee;
import com.exam.portal.Repository.AdminUserRepo;
import com.exam.portal.Repository.traineeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class TraineeController {

    @Autowired
    traineeRepository traineeRepository;
    @Autowired
	AdminUserRepo adminUserRepo;
    
    @GetMapping("/organiser/user")
    public String traineeView(Model model) {
        List<trainee> trainee= traineeRepository.findAll();
        model.addAttribute("trainee",trainee);
        return "trainee/trainee_view";
    }

    @GetMapping("/registerUser")
	public String RegisterUser(Model model) {
		List<trainee> trainee= traineeRepository.findAll();
        model.addAttribute("trainee",trainee);
	
		return "trainee/organization_user";
	}

    @PostMapping("/trainee_sign_up")
    public String TraineeSignup(@ModelAttribute trainee trainee) {
        AdminUser org=new AdminUser();
        org.setCreatedate(new Date());
        org.setName(trainee.getName());
        org.setEmail(trainee.getEmail());
        org.setPassword(new Encrypt().sha256(trainee.getPassword()));
        org.setRole("trainee");
        adminUserRepo.save(org);
        traineeRepository.save(trainee);
        
        return "redirect:/organiserlogin";
    }

    @GetMapping("/profile.html")
	public String Profile(Model model,Principal principal) {

		String email=principal.getName();
        System.out.println("email="+email);
        trainee user=traineeRepository.findByEmail(email);
		model.addAttribute("user", user);
		return "profile";
	}

    
    

    
    
}
