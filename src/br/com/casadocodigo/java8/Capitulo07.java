package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Capitulo07 {
    public static void main(String[] args) {
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

        for (Usuario usuario : usuarios) {
            if (usuario.getPontos() > 100) {
                usuario.tornaModerador();
            }
        }

        Stream<Usuario> stream = usuarios.stream();
        stream.filter(usuario -> usuario.getPontos() > 100);

        usuarios.stream()
                .filter(usuario -> usuario.getPontos() > 100)
                .forEach(System.out::println);

        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(Usuario::tornaModerador);

        System.out.println("-----------------------------------");
        System.out.println("Coletando os usuários com mais de 100 pontos");

        List<Usuario> listMaisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList());
        listMaisQue100.forEach(System.out::println);

        Set<Usuario> setMaisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toSet());
        setMaisQue100.forEach(System.out::println);

        Set<Usuario> colectCollection = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toCollection(HashSet::new));

        System.out.println("------------------------------------");
        System.out.println("Listando apenas os pontos de todos os usuários");

        List<Integer> pontos = usuarios.stream()
                .map(Usuario::getPontos)
                .collect(Collectors.toList());

        pontos.forEach(System.out::println);

        System.out.println("------------------------------------");
        double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
        System.out.println("Média dos pontos " + pontuacaoMedia);

        OptionalDouble media = usuarios.stream().mapToInt(Usuario::getPontos).average();
        pontuacaoMedia = media.orElse(0.0);

        pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElseThrow(IllegalStateException::new);

        System.out.println("------------------------------------");
        System.out.println("Usuário com mais pontos");
        Optional<String> maxNome = usuarios.stream()
                .max(Comparator.comparingInt(Usuario::getPontos))
                .map(Usuario::getNome);

        System.out.println(maxNome.get());
    }
}