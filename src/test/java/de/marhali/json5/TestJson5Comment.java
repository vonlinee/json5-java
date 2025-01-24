package de.marhali.json5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestJson5Comment {

    private Json5 json5;

    private InputStream getTestResource(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    private String getTestResourceContent(String fileName) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(getTestResource(fileName))) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();

            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }

            return buf.toString(StandardCharsets.UTF_8);
        }
    }

    @BeforeEach
    void setup() {
        json5 = Json5.builder(builder ->
                builder.allowInvalidSurrogate()
                        .quoteSingle()
                        .indentFactor(2)
                        .remainComment()
                        .build());
    }

    @Test
    void parseComment() {
        try (InputStream stream = getTestResource("test.comment.json5")) {
            Json5Element element = json5.parse(stream);
            if (element.isJson5Object()) {
                Json5Object object = element.getAsJson5Object();
                System.out.println(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
