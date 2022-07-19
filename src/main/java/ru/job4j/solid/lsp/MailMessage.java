package ru.job4j.solid.lsp;

public class MailMessage extends Message {
    private String str;

    public MailMessage(String str) {
        super(str);
        this.str = str;
    }

    /**
     * Предусловие усиляется в подклассе
     */
    public void printMessage() {
        if (this.str.length() < 5) {
            throw new IllegalArgumentException("Эта строка слишком короткая");
        }
        System.out.println(str);
    }

    /**
     * Постусловие ослабляется в подклассе
     * @return
     */
    public String sendMessage() {
        if (str.length() > 10000) {
            throw new IllegalArgumentException("Сообщение слишком большое");
        }
        return str;
    }
}
