package br.com.casadocodigo.java8;


/**
 * Interface funcional tem um único método abstrato. E pode ser instanciada por uma expressão lambda.
 *
 * @param <T>
 */
@FunctionalInterface
public interface Validador<T> {
    boolean valida(T t);
}
