package de.marhali.json5.stream;

public class CommentCache {

    StringBuilder textBuilder = new StringBuilder();

    public void append(char c) {
        textBuilder.append(c);
    }

    public void append(String chars) {
        textBuilder.append(chars);
    }

    public void clear() {
        textBuilder.delete(0, textBuilder.length());
    }

    public String getText() {
        return textBuilder.toString();
    }

    public boolean isEmpty() {
        return textBuilder.isEmpty();
    }
}
