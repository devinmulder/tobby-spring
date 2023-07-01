package springbook.learningtest;

public interface LineCallback<T> {
    T doSomethingWithLines(String line, T value);
}
