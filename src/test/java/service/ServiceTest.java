package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "teme.xml");
    private NotaXMLRepository notaRepository = new NotaXMLRepository(notaValidator, "note.xml");

    private Service service= new Service(studentsRepository, temaRepository, notaRepository);

    @BeforeEach
    void initService(){
        this.studentsRepository.deleteAll();
        this.temaRepository.deleteAll();
        studentsRepository.save(new Student(30, "Teodor", 937));
        temaRepository.save(new Tema(11,"tema lab 1", 14, 1 ));
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

    @Test
    void saveTemaOutsideIf(){
        assertEquals(0, this.service.saveTema(11, "tema lab 2", 12, 2));
    }

    @Test
    void saveTemaInsideIf(){
        assertEquals(1, this.service.saveTema(11, "tema lab 2", 1, 12));
    }

    @Test
    void saveStudentOk(){
        assertEquals(0, this.service.saveStudent(33,"Andrea",933));
    }

    @Test
    void saveTemaOk(){
        assertEquals(0, this.service.saveTema(11, "tema lab 2", 12, 2));
    }

    @Test
    void saveNotaOk(){
        assertEquals(0, this.service.saveNota(30,11, 10, 4, "good job"));
    }

    @Test
    void integrationSaveAllOk(){
        this.saveStudentOk();
        this.saveTemaOk();
        this.saveNotaOk();
    }

    @Test
    void integrationAddAssignmentOk(){
        this.saveStudentOk();
        this.saveTemaOk();
    }
}
