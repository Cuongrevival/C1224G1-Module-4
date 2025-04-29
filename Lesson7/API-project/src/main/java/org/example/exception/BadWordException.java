package org.example.exception;

public class BadWordException extends RuntimeException {
  private final String author;
  private final String comment;

  public BadWordException(String author, String comment) {
    super("Bad word detected");
    this.author = author;
    this.comment = comment;
  }

  public String getAuthor() {
    return author;
  }

  public String getComment() {
    return comment;
  }
}
