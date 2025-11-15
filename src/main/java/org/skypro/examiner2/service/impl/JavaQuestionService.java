package org.skypro.examiner2.service.impl;

import org.skypro.examiner2.entity.Question;
import org.skypro.examiner2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class JavaQuestionService implements QuestionService {
    Set<Question> questions=new TreeSet<>();
    @Override
    public Question add(String question, String answer) {
        Question q=new Question(question,answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null; // или кинуть исключение
        }

        List<Question> list = new ArrayList<>(questions);
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
