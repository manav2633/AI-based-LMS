package com.exam.portal.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.exam.portal.Model.Topic;
import com.exam.portal.Repository.topicRepository;


@Controller
public class topicController {
	@Autowired
	topicRepository repo;
	
	
	@GetMapping("/organiser/topic")
	public String showTopic(Model model) {
		
	List<Topic> org = repo.findAll();
	model.addAttribute("topics", org);
		return "organiser/topic/tlist";	
	}
	
	@GetMapping("/organiser/newtopic")
	public String newTopic(Model model) {
		model.addAttribute("topic", new Topic());
		return "organiser/topic/Topic";	
	}
	
	 @PostMapping("/upload")
	    public String saveUser(Topic topic,BindingResult result,
				Model model,          
	            @RequestParam("videofile") MultipartFile videofile, @RequestParam("transcriptfile") MultipartFile transcriptfile, 
				@RequestParam("pdffile") MultipartFile pdffile ) throws IOException {
	       try {  
	    	       	  
	    //    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	         String videofile1 = StringUtils.cleanPath(videofile.getOriginalFilename());
	         String transcriptfile1=StringUtils.cleanPath(transcriptfile.getOriginalFilename());
			 String pdffile1=StringUtils.cleanPath(pdffile.getOriginalFilename());
	         topic.setVideoFile(videofile1);
			 topic.setTranscriptFile(transcriptfile1);
			 topic.setPdfFile(pdffile1);
	         
	         repo.save(topic);
	         
	       
	        String uploadDir1 = "user-files/" ;
     
	        FileUploadUtil.saveFile(uploadDir1, videofile1, videofile);
			FileUploadUtil.saveFile(uploadDir1, transcriptfile1, transcriptfile);
	        FileUploadUtil.saveFile(uploadDir1, pdffile1, pdffile);

	        
	    	model.addAttribute("topic", new Topic());
			//session.setAttribute("message", new Message("Succesfully Registered !!!!","alert-success"));
			   
	        return "redirect:/organiser/topic";
	       }
	       catch (Exception e) {
			
	    	   e.printStackTrace();
				model.addAttribute("user", topic);
			//	session.setAttribute("message", new Message("something went wrong   " + e.getMessage(), "alert-danger"));
				return "organiser/organization";
		}
	    }

}
