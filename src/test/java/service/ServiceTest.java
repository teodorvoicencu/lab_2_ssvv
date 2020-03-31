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

    private Validator<Student> studentValidator = new StudentValidator();
    private Validator<Tema> temaValidator = new TemaValidator();
    private Validator<Nota> notaValidator = new NotaValidator();

    private StudentXMLRepository studentsRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
    private TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    private NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    private Service service= new Service(studentsRepository, fileRepository2, fileRepository3);

    @BeforeEach
    void initService(){
        this.studentsRepository.deleteAll();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE-1})
    void saveStudentIdOk(Integer id) {
        String name = "Ion Ionescu";
        Integer group = 937;
        assertEquals(0, this.service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void saveStudentIdFail(Integer id) {
        String name = "Ion Ionescu";
        Integer group = 937;
        assertEquals(1, this.service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ion Ionescu", "Andreea Vescan","Voicencu Teodor Octavian"})
    void saveStudentNameOk(String name) {
        Integer id = 1;
        Integer group = 937;
        assertEquals(0, this.service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "Ion123", "asd", "Ion A", "Ion    " })
    void saveStudentNameFail(String name) {
        Integer id = 1;
        Integer group = 937;
        assertEquals(1, this.service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(ints = {110,938,234, 111,937})
    void saveStudentGroupOk(Integer group) {
        Integer id = 1;
        String name = "Ion Ionescu";
        assertEquals(0, this.service.saveStudent(id,name,group));
    }

    @ParameterizedTest
    @ValueSource(ints = {109, 939})
    void saveStudentGroupFail(Integer group) {
        Integer id = 1;
        String name = "Ion Ionescu";
        assertEquals(1, this.service.saveStudent(id,name,group));
    }

}