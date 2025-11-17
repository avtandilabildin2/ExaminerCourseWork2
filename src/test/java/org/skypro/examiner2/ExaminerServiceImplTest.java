package org.skypro.examiner2;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.skypro.examiner2.entity.Question;
import org.skypro.examiner2.service.QuestionService;
import org.skypro.examiner2.service.impl.ExaminerServiceImpl;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;

class ExaminerServiceImplTest {

    private QuestionService questionService;
    private ExaminerServiceImpl examiner;

    private final Question q1 = new Question("Q1", "A1");
    private final Question q2 = new Question("Q2", "A2");
    private final Question q3 = new Question("Q3", "A3");

    @BeforeEach
    void setup() {
        questionService = Mockito.mock(QuestionService.class);
        examiner = new ExaminerServiceImpl(questionService);
    }

    @Test
    void returnsRequestedAmountOfUniqueQuestions() {
        Mockito.when(questionService.getAll())
                .thenReturn(Set.of(q1, q2, q3));

        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(q1, q1, q2, q3);

        Collection<Question> result = examiner.getQuestions(3);

        Assertions.assertEquals(3, result.size());
    }

    @Test
    void throwsBadRequestWhenAmountTooLarge() {
        Mockito.when(questionService.getAll())
                .thenReturn(Set.of(q1, q2));

        Assertions.assertThrows(ResponseStatusException.class,
                () -> examiner.getQuestions(3));
    }

    @Test
    void randomnessKeepsOnlyUniqueQuestions() {
        Mockito.when(questionService.getAll())
                .thenReturn(Set.of(q1, q2, q3));

        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(q1, q1, q1, q2, q3);

        Collection<Question> result = examiner.getQuestions(3);

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.contains(q1));
        Assertions.assertTrue(result.contains(q2));
        Assertions.assertTrue(result.contains(q3));
    }
}


