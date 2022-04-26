package br.com.casadocodigo.java8;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Capitulo08 {
    public static void main(String[] args) throws IOException {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 80);
        Usuario user4 = new Usuario("Gabriel", 195);
        Usuario user5 = new Usuario("Maria", 100);
        Usuario user6 = new Usuario("João", 90);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);

        System.out.println("Ordenando Stream");
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .forEach(System.out::println);

        System.out.println("---------------------------");
        System.out.println("Retornando qualquer usuário com mais de 100 pontos");
        Optional<Usuario> any = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .findAny();

        System.out.println(any.get());

        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .peek(System.out::println)
                .findAny();

        usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .peek(System.out::println)
                .findAny();

        System.out.println("---------------------------");
        System.out.println("Operações de redução(reduction)");
        Optional<Usuario> max = usuarios.stream()
                .max(Comparator.comparing(Usuario::getPontos));
        Usuario usuario = max.get();

        int soma = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .sum();
        System.out.println("Soma dos pontos: " + soma);

        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;

        int total1 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(valorInicial, operacao);

        int total2 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);

        int total3 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum);
        System.out.println(total3);

        int multiplicacao = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(1, (a, b) -> a * b);
        System.out.println(multiplicacao);

        // pulando o map
        int total4 = usuarios.stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);

        System.out.println("------------------------");
        System.out.println("java.nio.file.Files");
        System.out.println("Listando os arquivos do diretório");
        Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .forEach(System.out::println);

        System.out.println("--------------------------");
        Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .filter(p -> p.toString().endsWith(".java"))
                .map(p -> lines(p))
                .forEach(System.out::println);

        Stream<String> strings = Files.list(Paths.get("src/br/com/casadocodigo/java8"))
                .filter(p -> p.toString().endsWith(".java"))
                .flatMap(p -> lines(p));

    }
    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}