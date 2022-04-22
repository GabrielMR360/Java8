package br.com.casadocodigo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Capitulo04 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        // lista devolvida é imutável
//        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
//
//        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
//        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
//
//        usuarios.forEach(mostraMensagem.andThen(imprimeNome));

//        Predicate<Usuario> predicado = new Predicate<Usuario>() {
//            @Override
//            public boolean test(Usuario usuario) {
//                return usuario.getPontos() > 160;
//            }
//        };

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        usuarios.removeIf(u -> u.getPontos() > 160);
        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));
    }
}
