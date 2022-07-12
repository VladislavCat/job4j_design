package ru.job4j.solid;

import java.util.Objects;
import java.util.Scanner;

public class SprError {
    private static SprError sprError;
    private String stringExpression;

    /*
    SingleTone
    Наличие бизнес-логики в модели данных
     */

    private SprError(String stringExpression) {
        this.stringExpression = stringExpression;
    }

    public SprError initSprError(String s) {
        if (Objects.isNull(sprError)) {
            sprError = new SprError(s);
        }
        return sprError;
    }

    public String getStringExpression() {
        return stringExpression;
    }

    public void setStringExpression(String stringExpression) {
        this.stringExpression = stringExpression;
    }
}
