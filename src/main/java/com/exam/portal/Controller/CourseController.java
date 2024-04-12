package com.exam.portal.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.portal.Model.Course;
import com.exam.portal.Model.Program;
import com.exam.portal.Model.Topic;
import com.exam.portal.Model.modules;
import com.exam.portal.Repository.CourseRepository;
import com.exam.portal.Repository.ProgramRepository;
import com.exam.portal.Repository.moduleRepository;
import com.exam.portal.Repository.topicRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class CourseController {

	@Autowired
    CourseRepository repo;
	@Autowired
	ProgramRepository programrepo;

	@Autowired
	moduleRepository modulerepo;
	@Autowired
	topicRepository topicRepository;

	@GetMapping("/programs/{id}")
public String getEnrolledCourses(@PathVariable("id") Integer programId, Model model) {
    Optional<Program> optionalProgram = programrepo.findById(programId);
    if (optionalProgram.isPresent()) {
        Program program = optionalProgram.get();
        List<Course> courses = program.getCourses();
        List<Integer> courseIds = courses.stream().map(Course::getCourseId).collect(Collectors.toList());
        List<String> courseNames = courses.stream().map(Course::getCourseName).collect(Collectors.toList());
        
        model.addAttribute("courseIds", courseIds);
        model.addAttribute("courseNames", courseNames);
        
        return "courses";
    } else {
        // Handle program not found case
        return "userdashboard";
    }
}

	 @GetMapping("/course/{id}")
    public String getTopic(Model model) {
        List<Course> course = repo.findAll();
		List<Program> program = programrepo.findAll();
		List<Topic> topic= topicRepository.findAll();
		List<modules> module= modulerepo.findAll();
		model.addAttribute("module", module);
		model.addAttribute("topic", topic);
		model.addAttribute("program", program);
		model.addAttribute("course", course);
        return "courses-details"; 
	 }

	


	@GetMapping("/organiser/course")
	public String viewNodal(Model model) {

		List<String> categories = repo.findDistinctCourseCategory();
		model.addAttribute("categories", categories);
			List<Course> course = repo.findAll();
		model.addAttribute("course", course);
		
		return "organiser/course/cview";
	}

	@GetMapping("/organiser/cour")
    public String viewCourseByCategory(Model model, @RequestParam(required = false) String category) {
		List<String> categories = repo.findDistinctCourseCategory();
		model.addAttribute("categories", categories);
        List<Course> course;
        if (category != null && !category.isEmpty()) {
           // course = repo.findByCourseCategory(category);
		   course=repo.findAll();
        } else {
            course = repo.findAll();
        }
        model.addAttribute("course", course);
        return "organiser/course/cview";
    }

	@GetMapping("/organiser/course/new")
	public String newCourse(Model model) {
		List<String> categories = repo.findDistinctCourseCategory();
		model.addAttribute("categories", categories);
		model.addAttribute("modulelist", modulerepo.findAll());
		model.addAttribute("course", new Course());
		return "organiser/course/courseCreate";
	}

	@PostMapping("/createCourse")
	public String createcourse(@ModelAttribute("course") Course course) {
		//TODO: process POST request
		course.setCreatedDate(new Date());
		//course.setCreatortid(0);
		repo.save(course);
		return "redirect:/organiser/course/new";
	}
	
}
