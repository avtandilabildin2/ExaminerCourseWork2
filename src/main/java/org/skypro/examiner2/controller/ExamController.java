package org.skypro.examiner2.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.examiner.entity.Question;
import org.skypro.examiner.service.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExaminerService examinerService;
    @GetMapping("/get-questions")
    public Collection<Question> getQuestions(int amount){
        return examinerService.getQuestions(amount);
    }

}
