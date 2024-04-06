package com.exam.portal.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.portal.Model.Course;
import com.exam.portal.Repository.CourseRepository;
import com.exam.portal.Repository.moduleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class CourseController {

	@Autowired
    CourseRepository repo;

	@Autowired
	moduleRepository modulerepo;


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
