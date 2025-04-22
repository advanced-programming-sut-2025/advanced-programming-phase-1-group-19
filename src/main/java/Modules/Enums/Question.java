package Modules.Enums;

public enum Question {
    first("What was the name of your first teacher?"),
    second("What was the name of your childhood pet?"),
    third("What was the name of your elementary school?"),
    fourth("In which city were you born?"),
    fifth("What was the first movie you saw in a cinema?"),
    sixth("What was the model of your first mobile phone?"),
    seventh("What was the name of your childhood best friend?"),
    eighth("What street did you grow up on?"),
    ninth("What is your paternal grandmother’s name?"),
    tenth("What was your dream job as a child?");

    private final String question;
    private final int id;

    Question(String question) {
        this.question = question;
        this.id = this.ordinal() + 1;
    }

    public static Question getQuestion(int id) {
        for(Question question : values()) {
            if(question.id == id)
                return question;
        }
        return null;
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
