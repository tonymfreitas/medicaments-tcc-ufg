package br.com.ufg.tcc.medicamentos.common;

import com.google.common.io.Resources;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class QueryFileReader {

    private QueryFileReader() {}

    public static String getContentFromQueryFile(final String path) {
        final URL url = QueryFileReader.class.getResource("/sql" + path);
        try {
            return Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LoggerFactory.getLogger(QueryFileReader.class).warn(e.getMessage(), e);
        }
        return path;
    }
}
