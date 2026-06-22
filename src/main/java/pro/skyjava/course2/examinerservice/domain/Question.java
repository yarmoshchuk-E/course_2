package pro.skyjava.course2.examinerservice.domain;

import java.util.Objects;

public record Question(String question, String answer) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question that)) return false;

        return Objects.equals(this.question, that.question) &&
                Objects.equals(this.answer, that.answer);
    }

}