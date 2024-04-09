package com.exam.portal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.exam.portal.Model.trainee;
import com.exam.portal.Repository.traineeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class TraineeController {

    @Autowired
    traineeRepository traineeRepository;
    
    @GetMapping("/organiser/user")
    public String traineeView(Model model) {
        List<trainee> trainee= traineeRepository.findAll();
        model.addAttribute("trainee",trainee);
        return "trainee/trainee_view";
    }

    @PostMapping("/trainee_sign_up")
    public String TraineeSignup(@ModelAttribute trainee trainee) {
        traineeRepository.save(trainee);
        
        return "organiserlogin";
    }
    

    
    
}
