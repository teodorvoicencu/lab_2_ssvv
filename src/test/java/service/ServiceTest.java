package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void initService(){
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE-1})
    void saveStudentOk(Integer id) {
        String name = "Nume";
        int group = 937;
        assertEquals(0, service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void saveStudentFail(Integer id) {
        String name = "Nume";
        int group = 937;
        assertEquals(1, service.saveStudent(id,name,group));
    }
}