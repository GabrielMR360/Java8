package br.com.casadocodigo.java8;

public class Capitulo03 {
    public static void main(String[] args) {
        Validador<String> validadorDeCPF = new Validador<String>() {
            @Override
            public boolean valida(String valor) {
                return valor.matches("[0-9]{5}-[0-9]{3}");
            }
        };

        Validador<String> validadorCEP = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

        System.out.println(validadorCEP.valida("04101-300"));

        final int numero = 5;
        new Thread(() -> {
            System.out.println(numero);
        }).start();
    }
}
