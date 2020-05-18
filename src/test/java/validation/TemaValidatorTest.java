package validation;

        import domain.Tema;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.params.ParameterizedTest;
        import org.junit.jupiter.params.provider.ValueSource;

        import static org.junit.jupiter.api.Assertions.*;

class TemaValidatorTest {

    private TemaValidator temaValidator = new TemaValidator();

    @Test
    public void testWrongId() {
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(null, "tema lab 1", 10, 5)));
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(-1, "tema lab 1", 10, 5)));
    }

    @Test
    public void testWrongDescription() {
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(12, "", 10, 5)));
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(12, null, 10, 5)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 15, 4})
    public void testWrongDeadline(Integer deadline) {
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(12, "description", deadline, 5)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 15, 6})
    public void testWrongStartTime(Integer startline) {
        assertThrows(ValidationException.class, () -> this.temaValidator.validate(new Tema(12, "description", 5, startline)));
    }


    @Test
    public void testOkEntity() {
        assertDoesNotThrow(() -> this.temaValidator.validate(new Tema(12, "description", 14, 1)));
    }
}
