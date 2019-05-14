package ru.javawebinar.topjava.graduation.util.exception;

public class TooLateChangeVoteException extends RuntimeException {
    public TooLateChangeVoteException(String msg) {
        super(msg);
    }
}
