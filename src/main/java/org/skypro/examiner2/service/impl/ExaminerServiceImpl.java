package org.skypro.examiner2.service.impl;

import lombok.RequiredArgsConstructor;
import org.skypro.examiner2.entity.Question;
import org.skypro.examiner2.service.ExaminerService;
import org.skypro.examiner2.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;
    @Override
    public Collection<Question> getQuestions(int amount) {
        int total = questionService.getAll().size();

        if (amount > total) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Requested more questions than available"
            );
        }

        Set<Question> result = new HashSet<>();

        while (result.size() < amount) {
            Question random = questionService.getRandomQuestion();
            result.add(random);
        }

        return result;
    }
}
