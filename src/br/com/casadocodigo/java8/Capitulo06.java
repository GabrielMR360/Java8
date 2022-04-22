package br.com.casadocodigo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.*;

public class Capitulo06 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);
        Usuario user4 = new Usuario("Gabriel", 195);
        Usuario user5 = new Usuario("Maria", 170);
        Usuario user6 = new Usuario("João", 90);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);
        
        // Method reference
        usuarios.forEach(Usuario::tornaModerador);

        System.out.println("Ordenando pelo nome");
        usuarios.sort(comparing(Usuario::getNome));

        Function<Usuario, String> byName = Usuario::getNome;
        usuarios.sort(comparing(byName));

        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("---------------------");
        System.out.println("Ordenando pelos pontos");
        usuarios.sort(comparingInt(Usuario::getPontos));

        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("--------------------");
        System.out.println("Comparando pelos pontos e pelo nome");

        usuarios.sort(comparing(Usuario::getPontos).thenComparing(Usuario::getNome));

        usuarios.forEach(u -> System.out.println(u.getNome()));

        System.out.println("----------------------");
        usuarios.sort(nullsLast(comparing(Usuario::getNome)));
        usuarios.forEach(u -> System.out.println(u.getNome()));

        usuarios.sort(comparingInt(Usuario::getPontos).reversed());
        usuarios.forEach(u -> System.out.println(u.getPontos()));

        usuarios.forEach(System.out::println);

        //Constructor reference
        Supplier<Usuario> criadorDeUsuarios = Usuario::new;
        Usuario novo1 = criadorDeUsuarios.get();

        // Criando um novo usuario a partir do construtor com um argumento;
        Function<String, Usuario> novoUsuario1 = Usuario::new;
        Usuario gabriel = novoUsuario1.apply("Gabriel");

        // Criando um novo usuario a partir do construtor com dois argumentos;
        BiFunction<String, Integer, Usuario> novoUsuario2 = Usuario::new;
        Usuario rodrigo = novoUsuario2.apply("Rodrigo", 100);
    }
}
