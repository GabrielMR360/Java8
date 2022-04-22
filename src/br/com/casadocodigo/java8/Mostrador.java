package br.com.casadocodigo.java8;

import java.util.function.Consumer;

public class Mostrador implements Consumer<Usuario> {
    /**
     * Pega um objeto de um determinado tipo e consome ele. "Consumir" aqui é realizar alguma tarefa que faça sentido.
     * Nesse caso é mostrar o nome do usuário na saída padrão.
     *
     * @param usuario  objeto a ser "consumido".
     */
    @Override
    public void accept(Usuario usuario) {
        System.out.println(usuario.getNome());
    }
}
