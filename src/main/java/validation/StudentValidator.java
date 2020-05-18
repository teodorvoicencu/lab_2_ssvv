package validation;

import domain.Student;
import org.jetbrains.annotations.NotNull;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        this.validateId(student.getID());
        this.validateName(student.getNume());
        this.validateGroup(student.getGrupa());
    }

    public void validateId(@NotNull Integer id) {
        if (id < 1) {
            throw new ValidationException("ID invalid! \n");
        }
    }

    public void validateName(@NotNull String name) {
        if (name.equals("") || !name.matches("^[A-Z][-a-zA-Z]{2,}( ?[a-zA-Z]{3,})*$")) {
            throw new ValidationException("Nume invalid! \n");
        }
    }

    public void validateGroup(@NotNull Integer group) {
        if (group < 110 || group > 938) {
            throw new ValidationException("Grupa invalida! \n");
        }
    }
}

