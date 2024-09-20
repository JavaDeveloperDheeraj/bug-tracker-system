package org.nic.bug_tracker_system.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

	
	
	
	@GetMapping("/")
    public String index(Model model) {
		model.addAttribute("pageName", "index");
		model.addAttribute("section","section") ;
		return "index";
    }
	
	@GetMapping("/taskManager")
    public String taskManager(Model model) {
		model.addAttribute("pageName", "taskManager");
		model.addAttribute("section","taskManager") ;
		return "index";
    }
	
	@GetMapping("/login")
    public String Login(Model model) {
		model.addAttribute("pageName", "Login");
		model.addAttribute("section","Login") ;
		return "index";
    }
	
	/*
	 * @GetMapping("/WelcomePage") public String WelcomePage(Model model) {
	 * model.addAttribute("pageName", "WelcomePage");
	 * model.addAttribute("section","WelcomePage") ; return "index"; }
	 */
	
	@GetMapping("/WelcomePage")
    public String showWelcomePage(Model model) {
        model.addAttribute("isWelcomePage", true);
        model.addAttribute("pageName", "WelcomePage");
		model.addAttribute("section","WelcomePage") ;// Pass a variable indicating it's the WelcomePage
        return "index"; // The name of your welcome page template
    }
	

	@GetMapping("/Register")
    public String Register(Model model) {
		model.addAttribute("pageName", "Register");
		model.addAttribute("section","Register") ;
		return "index";
    }
	
	@GetMapping("/issueList")
    public String issueList(Model model) {
		model.addAttribute("pageName", "issueList");
		model.addAttribute("section","issueList") ;
		return "index";
    }
	
	
}