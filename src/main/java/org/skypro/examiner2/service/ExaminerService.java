package org.skypro.examiner2.service;


import org.skypro.examiner2.entity.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
