package de.marhali.json5;

/**
 * Single and multi-line comments are allowed.
 */
public class Json5Comment {

    /**
     * 注释文本
     */
    private String text;

    public Json5Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
