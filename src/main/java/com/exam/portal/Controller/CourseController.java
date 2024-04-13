package com.exam.portal.Controller;

import java.security.Principal;
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

import com.exam.portal.Model.AdminUser;
import com.exam.portal.Model.Course;
import com.exam.portal.Model.Program;
import com.exam.portal.Model.Topic;
import com.exam.portal.Model.modules;
import com.exam.portal.Repository.AdminUserRepo;
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
	AdminUserRepo adminUserRepo;

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
	public String getModulesByCourseId(@PathVariable("id") Integer courseId, Model model) {
		Optional<Course> optionalCourse = repo.findByCourseId(courseId);
		if (optionalCourse.isPresent()) {
			Course course = optionalCourse.get();
			List<modules> modules = course.getModules();
			model.addAttribute("courseName", course.getCourseName());
			model.addAttribute("modules", modules);
			return "courses-details"; // Assuming this is your view name
		} else {
			// Handle course not found case
			return "redirect:/userdashboard"; // Redirect to home page or handle appropriately
		}
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
			course = repo.findAll();
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
		// TODO: process POST request
		course.setCreatedDate(new Date());
		// course.setCreatortid(0);
		repo.save(course);
		return "redirect:/organiser/course/new";
	}

	@GetMapping("/organiser/course/edit")
	public String editCourse(@RequestParam(name = "id") Integer id, Model model) {
		Optional<Course> course = repo.findByCourseId(id);

		if (course.isPresent()) {
			Course courses = course.get();

			model.addAttribute("courses", courses);
			model.addAttribute("moduleList", modulerepo.findAll());
			return "organiser/course/edit_course"; // Assuming this is your edit page
		} else {
			// Handle program not found case
			return "redirect:/organiser/course"; // Assuming you have a Thymeleaf template named programNotFound.html
		}

	}

	@PostMapping("/organiser/course/edit")
	public String saveEditedCourse(@ModelAttribute("courses") Course course,
			@RequestParam("selectedModule") List<Integer> selectedModules,
			Principal principal) {
		// if (course.getCourseId()==null) {
		// // Handle the case where the ID is null
		// // Redirect or show an error message
		// return "redirect:/organiser/dashboard";
		// }

		Optional<Course> optionalCourse = repo.findByCourseId(course.getCourseId());
		if (optionalCourse.isPresent()) {
			// AdminUser adminuser = adminUserRepo.findByEmail(principal.getName());
			Course existingCourse = optionalCourse.get();
			existingCourse.setCourseName(course.getCourseName());
			existingCourse.setCourseDescription(course.getCourseDescription());
			existingCourse.setCreatedDate(new Date());
			existingCourse.setCourseAbbrevation(course.getCourseAbbrevation());

			// Update the courses for the program
			List<modules> updatedModules = modulerepo.findAllById(selectedModules);
			existingCourse.setModules(updatedModules);

			repo.save(existingCourse);
		} else {
			// Handle the case where the program with the given ID does not exist
			// Redirect or show an error message
			return "redirect:/organiser/dashboard";
		}

		// Redirect to a confirmation page or another appropriate page
		return "redirect:/organiser/course";
	}

}
