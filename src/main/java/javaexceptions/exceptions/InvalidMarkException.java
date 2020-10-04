package javaexceptions.exceptions;

public class InvalidMarkException extends UniversityException {
    public InvalidMarkException() {
        super("Mark <0 or >10");
    }
}
