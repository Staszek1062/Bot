package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

    public class ResourceFileReader {

        public List<String> readLines(String fileName) {
            try {
                return Files.readAllLines(getPathToResourceFile(fileName));
            } catch (IOException ioException) {
                throw new RuntimeException("Error while reading lines from file", ioException);
            }
        }

        public Stream<String> readLinesAsStream(String fileName) {
            try {
                return Files.newBufferedReader(getPathToResourceFile(fileName)).lines();
            } catch (IOException ioException) {
                throw new RuntimeException("Error while reading lines from file", ioException);
            }
        }

        private Path getPathToResourceFile(String fileName) {
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println(Path.of(getUri(fileName, classLoader)));
            return Path.of(getUri(fileName, classLoader));
        }

        private URI getUri(String fileName, ClassLoader classLoader) {
            try {
                return Objects.requireNonNull(classLoader.getResource(fileName)).toURI();
            } catch (URISyntaxException uriSyntaxException) {
                throw new RuntimeException("Error with path to file", uriSyntaxException);
            }
        }
    }


