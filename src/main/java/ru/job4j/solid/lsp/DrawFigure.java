package ru.job4j.solid.lsp;

public class DrawFigure {
    /**
     *@param figure
     * Нарушение LSP так-как под определенное поведение требуются дополнительные проверки.
     * В таком случае метод draw стоит вывести в интерфейс и вызывать его из абстракции;
     */
    public void drawFigure(Figure figure) {
        if (figure instanceof Rectangle) {
            ((Rectangle) figure).drawRectangle();
        } else {
            ((Square) figure).drawSquare();
        }
    }

    /**
     * @param figure
     * Правильная реализация проблемы наследования квадрата от прямоугольника.
     * Метод perimeter выносится в отдельный интерфейс и представление perimeter определяется в каждой реализации, как и поля,
     * Так как два поля(длина и ширина) подходят для прямоугольника, но избыточны для квадрата
     */
    public void perimeterFigure(Figure figure) {
        System.out.println(figure.perimeter());
    }
}
