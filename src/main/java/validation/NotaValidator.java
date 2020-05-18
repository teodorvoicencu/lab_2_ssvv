package validation;
import domain.Nota;
import org.jetbrains.annotations.NotNull;

public class NotaValidator implements Validator<Nota> {
    public void validate(Nota nota) throws ValidationException {
        this.validateStudentId(nota.getID().getObject1());
        this.validateTemaId(nota.getID().getObject2());
        this.validateNota(nota.getNota());
        this.validateWeek(nota.getSaptamanaPredare());
    }

    public void validateStudentId(@NotNull Integer studentId){
        if (studentId < 1) {
            throw new ValidationException("ID Student invalid! \n");
        }
    }

    public void validateTemaId(@NotNull Integer temaId){
        if (temaId < 1) {
            throw new ValidationException("ID Tema invalid! \n");
        }
    }

    public void validateNota(@NotNull Double nota){
        if (nota < 0 || nota > 10) {
            throw new ValidationException("Nota invalida! \n");
        }
    }

    public void validateWeek(@NotNull Integer week){
        if (week < 0) {
            throw new ValidationException("Saptamana de predare invalida! \n");
        }
    }
}
