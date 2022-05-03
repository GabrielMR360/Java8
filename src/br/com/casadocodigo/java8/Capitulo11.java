package br.com.casadocodigo.java8;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Product {
    private String name;
    private Path file;
    private BigDecimal price;

    public Product(String name, Path file, BigDecimal price) {
        this.name = name;
        this.file = file;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Path getFile() {
        return file;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class Payment {
    private List<Product> products;
    private LocalDateTime date;
    private Customer customer;

    public Payment(List<Product> products, LocalDateTime date, Customer customer) {
        this.products = Collections.unmodifiableList(products);
        this.date = date;
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "[Payment: " +
                date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) +
                ", customer: " + this.customer +
                ", products: " + this.products + ']';
    }
}

public class Capitulo11 {
    public static void main(String[] args) {
        Customer gabriel = new Customer("Gabriel");
        Customer maria = new Customer("Maria");
        Customer rodrigo = new Customer("Rodrigo");
        Customer paulo = new Customer("Paulo");

        Product vingadores = new Product("Os Vingadores",
                Paths.get("/movies/vingadores.mov"), new BigDecimal(100));
        Product homesDeFerro = new Product("Homem de Ferro",
                Paths.get("/movies/homemdeferro.mov"), new BigDecimal(90));
        Product bandeira = new Product("Bandeira Brasil",
                Paths.get("/images/brasil.png"), new BigDecimal(110));
        Product dancin = new Product("Dancin",
                Paths.get("/music/dancin.mp3"), new BigDecimal(50));

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        LocalDateTime lastMonth = today.minusMonths(1);

        Payment payment1 = new Payment(Arrays.asList(vingadores, dancin), today, gabriel);
        Payment payment2 = new Payment(Arrays.asList(bandeira, dancin, vingadores), yesterday, maria);
        Payment payment3 = new Payment(Arrays.asList(vingadores, homesDeFerro, dancin), lastMonth, rodrigo);
        Payment payment4 = new Payment(Arrays.asList(homesDeFerro, bandeira), today, paulo);

        List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4);

        payments.stream()
                .sorted(Comparator.comparing(Payment::getDate))
                .forEach(System.out::println);

        payment1.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);

        BigDecimal total = payment1.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalFlat = payments.stream().
                flatMap(p -> p.getProducts().stream().map(Product::getPrice))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Valor total de pagamentos R$" + totalFlat);

        Map<Product, Long> topProducts = payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("\nProdutos mais vendidos");
        topProducts.entrySet().forEach(System.out::println);

        topProducts.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(System.out::println);

        Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.reducing(BigDecimal.ZERO, Product::getPrice,
                                BigDecimal::add)));

        System.out.println("\nValor total por produto");

        totalValuePerProduct.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        System.out.println("\nProdutos de cada cliente");
        Map<Customer, List<List<Product>>> customerToProductList = payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.mapping(Payment::getProducts, Collectors.toList())));

//        customerToProductList.entrySet().stream()
//                .sorted(Comparator.comparing(e -> e.getKey().getName()))
//                .forEach(System.out::println);

        Map<Customer, List<Product>> customerToProducts2steps = customerToProductList.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .flatMap(List::stream)
                                .collect(Collectors.toList())));

        customerToProducts2steps.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .forEach(System.out::println);

        Map<Customer, List<Product>> customerToProducts1step = payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.mapping(Payment::getProducts, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .flatMap(List::stream)
                                .collect(Collectors.toList())));

        System.out.println("\nTotal gasto por cada cliente");
        Function<Payment, BigDecimal> paymentToTotal =
                p -> p.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.reducing(BigDecimal.ZERO,
                                paymentToTotal,
                                BigDecimal::add)));

        totalValuePerCustomer.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);
    }
}
