package Modules.Enums;

public enum Question {
    ;

    private final String question;
    private final int id;

    Question(String question) {
        this.question = question;
        this.id = this.ordinal() + 1;
    }

    @Override
    public String toString() {
        return this.question;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }
}
