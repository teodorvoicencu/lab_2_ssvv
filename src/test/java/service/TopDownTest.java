package service;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopDownTest {

    private Integer studentId = 22;
    private String studentName = "Ionescu Popescu";
    private Integer studentGroup = 937;

    private Integer temaId = 11;
    private String temaDescription = "Team Laborator";
    private Integer temaDeadline = 12;
    private Integer temaStartline = 10;

    private Double notaValue = 10.0;
    private Integer notaPredata = 13;
    private String notaFeedback = "Foarte bine";

    private StudentValidator studentValidator = new StudentValidator();
    private TemaValidator temaValidator = new TemaValidator();
    private NotaValidator notaValidator = new NotaValidator();

    private StudentXMLRepository studentsRepository;
    private TemaXMLRepository temaRepository;
    private NotaXMLRepository notaRepository;

    private Service service;

    @BeforeEach
    void beforeEach() {
        this.studentsRepository = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        studentsRepository.deleteAll();

        this.temaRepository = new TemaXMLRepository(temaValidator, "teme_test.xml");
        temaRepository.deleteAll();

        this.notaRepository = new NotaXMLRepository(notaValidator, "note_test.xml");
        notaRepository.deleteAll();

        this.service = new Service(studentsRepository, temaRepository, notaRepository);
    }

    void validateStudent(){
        assertDoesNotThrow(() -> studentValidator.validateId(studentId), "ID invalid! \n");
        assertDoesNotThrow(() -> studentValidator.validateName(studentName), "Nume invalid! \n");
        assertDoesNotThrow(() -> studentValidator.validateGroup(studentGroup), "Grupa invalida! \n");
        assertDoesNotThrow(() -> studentValidator.validate(new Student(studentId, studentName, studentGroup)));
    }


    void testAddStudent() {
        assertEquals(this.studentsRepository.size(), 0);
        assertEquals(this.service.saveStudent(studentId, studentName, studentGroup), 0);
        assertEquals(this.studentsRepository.size(), 1);
    }

    void validateAssignment(){
        assertDoesNotThrow(() -> temaValidator.validateId(temaId), "ID invalid! \n");
        assertDoesNotThrow(() -> temaValidator.validateDescription(temaDescription), "Descriere invalida! \n");
        assertDoesNotThrow(() -> temaValidator.validateDeadline(temaDeadline, temaStartline), "Deadline invalid! \n");
        assertDoesNotThrow(() -> temaValidator.validateStartline(temaStartline, temaDeadline), "Data de primire invalida! \n");
        assertDoesNotThrow(() -> temaValidator.validate(new Tema(temaId, temaDescription, temaDeadline, temaStartline)));
    }

    void testAddAssignment() {
        assertEquals(this.temaRepository.size(), 0);
        assertEquals(this.service.saveTema(temaId, temaDescription, temaDeadline, temaStartline), 0);
        assertEquals(this.temaRepository.size(), 1);
    }

    void testAssignment(){
        this.validateAssignment();
        this.testAddAssignment();
    }

    void validateMark(){
        assertDoesNotThrow(() -> notaValidator.validateStudentId(studentId), "ID Student invalid! \n");
        assertDoesNotThrow(() -> notaValidator.validateTemaId(temaId), "ID Tema invalid! \n");
        assertDoesNotThrow(() -> notaValidator.validateNota(notaValue), "Nota invalida! \n");
        assertDoesNotThrow(() -> notaValidator.validateWeek(notaPredata), "Saptamana de predare invalida! \n");
        assertDoesNotThrow(() -> notaValidator.validate(new Nota(new Pair<>(studentId, temaId), notaValue, notaPredata, notaFeedback)));
    }

    void testAddMark() {
        assertEquals(this.notaRepository.size(), 0);
        assertEquals(this.service.saveNota(studentId, temaId, notaValue, notaPredata, notaFeedback), 0);
        assertEquals(this.notaRepository.size(), 1);
    }

    void testMark(){
        this.validateMark();
        this.testAddMark();
    }

    @Test
    void testStudent(){
        this.validateStudent();
        this.testAddStudent();
    }

    @Test
    void testStudentAssignment(){
        this.testStudent();
        this.testAssignment();
    }

    @Test
    void testStudentAssignmentMark(){
       this.testStudent();
       this.testAssignment();
       this.testMark();
    }
}
