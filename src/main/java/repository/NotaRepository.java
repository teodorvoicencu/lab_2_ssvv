package repository;

import domain.*;
import validation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NotaRepository extends AbstractCRUDRepository<Pair<Integer, Integer>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
