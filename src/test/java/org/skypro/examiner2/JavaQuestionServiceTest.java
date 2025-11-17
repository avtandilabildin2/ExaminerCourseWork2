package org.skypro.examiner2;

import org.junit.jupiter.api.*;
import org.skypro.examiner2.entity.Question;
import org.skypro.examiner2.service.impl.JavaQuestionService;

import java.util.Collection;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setup() {
        service = new JavaQuestionService();
    }

    @Test
    void addQuestionStringMethodAddsQuestion() {
        Question q = service.add("Q1", "A1");

        Collection<Question> all = service.getAll();

        Assertions.assertEquals(1, all.size());
        Assertions.assertTrue(all.contains(q));
    }

    @Test
    void addQuestionObjectMethodAddsQuestion() {
        Question q = new Question("Q1", "A1");
        service.add(q);

        Assertions.assertTrue(service.getAll().contains(q));
    }

    @Test
    void removeExistingQuestionRemovesSuccessfully() {
        Question q = new Question("Q1", "A1");

        service.add(q);
        service.remove(q);

        Assertions.assertFalse(service.getAll().contains(q));
    }

    @Test
    void removeNonExistingQuestionDoesNothingOrThrows() {
        Question q = new Question("Q1", "A1");

        // по твоей реализации remove НЕ бросает исключение
        service.remove(q);

        Assertions.assertFalse(service.getAll().contains(q));
    }

    @Test
    void getRandomQuestionReturnsOneOfExisting() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        service.add("Q3", "A3");

        Question random = service.getRandomQuestion();

        Assertions.assertNotNull(random);
        Assertions.assertTrue(service.getAll().contains(random));
    }

    @Test
    void getRandomQuestionReturnsNullIfEmpty() {
        Question random = service.getRandomQuestion();
        Assertions.assertNull(random);
    }
}
