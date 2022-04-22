package br.com.casadocodigo.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Capitulo05 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        // Dado um tipo T , o comparing recebe um lambda que devolve um tipo U
        usuarios.sort(Comparator.comparing(u -> u.getNome()));

        usuarios.forEach(u -> System.out.println(u.getNome()));

        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");

        palavras.sort(Comparator.naturalOrder());

        palavras.forEach(p -> System.out.println(palavras));

        Function<Usuario, String> extraiNome = u -> u.getNome();
        Comparator<Usuario> comparator = Comparator.comparing(extraiNome);
        usuarios.sort(comparator);

        usuarios.sort(Comparator.comparing(u -> u.getPontos()));

        usuarios.forEach(u -> System.out.println(u.getPontos()));

        Function<Usuario, Integer> extraiPontos = usuario -> usuario.getPontos();
        Comparator<Usuario> comparator1 = Comparator.comparing(extraiPontos);

        usuarios.sort(comparator1);

        ToIntFunction<Usuario> extraiPontos1 = u -> u.getPontos();
        Comparator<Usuario> comparator2 = Comparator.comparingInt(extraiPontos1);

        usuarios.sort(comparator2);

        usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
    }
}
