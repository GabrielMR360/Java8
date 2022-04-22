package br.com.casadocodigo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Capitulo02 {
    public static void main(String[] args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome());
        }

        usuarios.forEach(new Consumer<Usuario>() {
            @Override
            public void accept(Usuario usuario) {
                System.out.println(usuario.getNome());
            }
        });

        /**
         * Lambda é uma maneira de mais simples de implementar uma interface que só tem um único método abstrato.
         */
        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));
        usuarios.forEach(usuario -> usuario.tornaModerador());

    }
}
