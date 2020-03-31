package domain;


import java.util.Objects;

public class Student implements HasID<Integer> {
    private Integer idStudent;

    private String nume;

    private Integer grupa;

    public Student(Integer idStudent, String nume, Integer grupa) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
    }

    @Override
    public Integer getID() { return idStudent; }

    @Override
    public void setID(Integer idStudent) { this.idStudent = idStudent; }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "Student{" + "idStudent=" + idStudent + ", nume='" + nume + '\'' + ", grupa=" + grupa + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent);
    }
}

