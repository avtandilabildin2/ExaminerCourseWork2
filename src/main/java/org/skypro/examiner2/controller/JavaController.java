package org.skypro.examiner2.controller;

import lombok.RequiredArgsConstructor;
import org.skypro.examiner2.entity.Question;
import org.skypro.examiner2.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam/java")
@RequiredArgsConstructor
public class JavaController {
    private final QuestionService questionService;
    @PostMapping("/add")
    public Question addQuestion(@RequestParam String question,@RequestParam String answer){
        return questionService.add(question,answer);
    }
    @GetMapping
    public Iterable<Question> getAllQuestions(){
        return questionService.getAll();
    }
    @GetMapping("/get-random")
    public Question getRandomQuestion(){
        return questionService.getRandomQuestion();
    }
    @DeleteMapping("/remove")
    public Question removeQuestion(Question question){
        return questionService.remove(question);
    }

}
