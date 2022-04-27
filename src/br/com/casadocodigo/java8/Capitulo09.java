package br.com.casadocodigo.java8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Capitulo09 {
    public static void main(String[] args) throws IOException {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 80);
        Usuario user4 = new Usuario("Gabriel", 195);
        Usuario user5 = new Usuario("Maria", 120);
        Usuario user6 = new Usuario("João", 90);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);

        LongStream lines = Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .filter(p -> p.toString().endsWith(".java"))
                .mapToLong(p -> lines(p).count());

        Map<Path, Long> linesPerFile = new HashMap<>();

        Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> linesPerFile.put(p, lines(p).count()));

        System.out.println(linesPerFile);

        Map<Path, Long> linesCollect = Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .filter(p -> p.toString().endsWith(".java"))
                .collect(Collectors.toMap(p -> p, p -> lines(p).count()));

        System.out.println(linesCollect);

        Map<String, Usuario> nameToUser = usuarios.stream()
                .collect(Collectors.toMap(Usuario::toString, Function.identity()));

        // Agrupando usuários pelos pontos;
        Map<Integer, List<Usuario>> pontuacao = usuarios.stream()
                .collect(Collectors.groupingBy(Usuario::getPontos));

        System.out.println(pontuacao);


        // Particionando usuários entre moderadores e não moderadores;
        user1.tornaModerador();
        user2.tornaModerador();

        Map<Boolean, List<Usuario>> moderadores = usuarios.stream()
                .collect(Collectors.partitioningBy(Usuario::isModerador));

        System.out.println(moderadores);

        Map<Boolean, List<String>> nomesPorTipo = usuarios.stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.mapping(Usuario::getNome,
                                        Collectors.toList())));

        System.out.println(nomesPorTipo);

        Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.summingInt(Usuario::getPontos)));

        System.out.println(pontuacaoPorTipo);

        String nomes = usuarios
                .stream()
                .map(Usuario::getNome)
                .collect(Collectors.joining(", "));

        System.out.println(nomes);
    }

    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
