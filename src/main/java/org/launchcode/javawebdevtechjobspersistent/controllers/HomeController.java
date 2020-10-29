package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                        Errors errors, Model model, @RequestParam Integer employerId) { //TODO: @RequestParam List<Integer> skills <--- RE-ADD as last param

        if(!errors.hasErrors()) {
            Employer employer = newJob.getEmployer();
            //Skill skill = newJob.getSkills();
            if(employerRepository.existsById(employerId)) {
                Optional<Employer> result = employerRepository.findById(employerId);
                Employer emp = result.get();
                if(!emp.getJobs().contains(newJob)) {
                    newJob.setEmployer(emp);
                    jobRepository.save(newJob);
                }
            }
        }


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }



        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable Integer jobId) {

        return "view";
    }


}
