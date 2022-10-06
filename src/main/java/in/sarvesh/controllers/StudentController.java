package in.sarvesh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sarvesh.models.Student;
import in.sarvesh.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("Students", theStudents); 
		return "list-Students";
	}
		
		@RequestMapping("/search")
		public String search(@RequestParam("name") String name, @RequestParam("department") String department, Model theModel) {

			// check names, if both are empty then just give list of all Books

			if (name.trim().isEmpty() && department.trim().isEmpty()) {
				return "redirect:/books/list";
			} else {
				// else, search by first name and last name
				List<Student> theStudents = studentService.searchBy(name, department);

				// add to the spring model
				theModel.addAttribute("Students", theStudents);

				// send to list-Books
				return "list-Students";
			}

		}
		
		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {

			// create model attribute to bind form data
			Student theStudent = new Student();

			theModel.addAttribute("Student", theStudent);

			return "Student-form";
		}

		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

			// get the Book from the service
			Student theStudent = studentService.findById(theId);

			// set Book as a model attribute to pre-populate the form
			theModel.addAttribute("Student", theStudent);

			// send over to our form
			return "Student-form";
		}

		@PostMapping("/save")
		public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name,
				@RequestParam("country") String country, @RequestParam("department") String department) {

			System.out.println(id);
			Student theStudent;
			if (id != 0) {
				theStudent = studentService.findById(id);
				theStudent.setName(name);
				theStudent.setCountry(country);
				theStudent.setDepartment(department);
			} else
				theStudent = new Student(name, country, department);
			// save the Book
			studentService.save(theStudent);

			// use a redirect to prevent duplicate submissions
			return "redirect:/students/list";

		}

		@RequestMapping("/delete")
		public String delete(@RequestParam("studentId") int theId) {

			// delete the Book
			studentService.deleteById(theId);

			// redirect to /Books/list
			return "redirect:/students/list";

		}

}
