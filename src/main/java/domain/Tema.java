package domain;

import java.util.Objects;

public class Tema implements HasID<Integer> {
    private Integer idTema;
    private String descriere;
    private int deadline;
    private int startline;

    public Tema(Integer idTema, String descriere, int deadline, int startline) {
        this.idTema = idTema;
        this.descriere = descriere;
        this.deadline = deadline;
        this.startline = startline;
    }

    @Override
    public Integer getID() { return idTema; }

    @Override
    public void setID(Integer idTema) { this.idTema = idTema; }

    public String getDescriere() { return descriere; }

    public void setDescriere(String descriere) { this.descriere = descriere; }

    public Integer getDeadline() { return deadline; }

    public void setDeadline(int deadline) { this.deadline = deadline; }

    public Integer getStartline() { return startline; }

    public void setStartline(int startline) { this.startline = startline; }

    @Override
    public String toString() {
        return "Tema{" + "id='" + idTema + "', descriere='" + descriere + ", deadline=" + deadline +
                ", startline=" + startline + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tema tema = (Tema) o;
        return Objects.equals(idTema, tema.idTema);
    }

    @Override
    public int hashCode() { return Objects.hash(idTema); }
}
