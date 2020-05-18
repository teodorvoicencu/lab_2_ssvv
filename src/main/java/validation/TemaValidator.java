package validation;
import domain.Tema;
import org.jetbrains.annotations.NotNull;

public class TemaValidator implements Validator<Tema> {
    public void validate(Tema tema) throws ValidationException {
        this.validateId(tema.getID());
        this.validateDescription(tema.getDescriere());
        this.validateDeadline(tema.getDeadline(), tema.getStartline());
        this.validateStartline(tema.getStartline(), tema.getDeadline());
    }

    public void validateId(@NotNull Integer id){
        if (id < 1) {
            throw new ValidationException("ID invalid! \n");
        }
    }

    public void validateDescription(@NotNull String description){
        if (description.equals("")) {
            throw new ValidationException("Descriere invalida! \n");
        }
    }

    public void validateDeadline(@NotNull Integer deadline, @NotNull Integer startline){
        if (deadline < 1 || deadline > 14 || deadline < startline) {
            throw new ValidationException("Deadline invalid! \n");
        }
    }

    public void validateStartline(@NotNull Integer startline, @NotNull Integer deadline){
        if (startline < 1 || startline > 14 || startline > deadline) {
            throw new ValidationException("Data de primire invalida! \n");
        }
    }
}

