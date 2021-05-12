package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepo;

    @GetMapping("/schools")
    public String getAll(Model model) {
        model.addAttribute("schools", schoolRepo.findAll());
        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {
        School mySchool = new School();
        
        if (id!=null) {
            Optional<School> oSchool = schoolRepo.findById(id);
            if (oSchool.isPresent()) {
                mySchool = oSchool.get();
            }
        }
        model.addAttribute("school", mySchool);
       
        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {
        school = schoolRepo.save(school);
        System.out.println("school saved : id : " + school.getId());
        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
        schoolRepo.deleteById(id);
        return "redirect:/schools";
    }
}
