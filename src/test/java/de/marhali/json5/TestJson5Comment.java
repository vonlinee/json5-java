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
                        .build()
                        .remainComment(false));
    }

    @Test
    void parseComment() {
        try (InputStream stream = getTestResource("test.comment.json5")) {
            Json5Element element = json5.parse(stream);

            if (element.isJson5Object()) {
                Json5Object asJson5Object = element.getAsJson5Object();

                Json5Array array = asJson5Object.getAsJson5Array("Data");

                Json5Object object = array.getAsJson5Object(0);

                for (String key : object.keySet()) {
                    System.out.println(key);
                    System.out.println(object.getComment(key));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
