package br.com.casadocodigo.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Capitulo10 {
    public static void main(String[] args) {
        LocalDate mesQueVem = LocalDate.now().plusMonths(1);
        System.out.println(mesQueVem);

        LocalDate anoPassado = LocalDate.now().minusYears(1);
        System.out.println(anoPassado);

        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora);

        LocalTime hora = LocalTime.now();
        System.out.println(hora);

        LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12, 0);
        System.out.println(hojeAoMeioDia);

        LocalDate hoje = LocalDate.now();
        LocalDateTime dataEHora = hoje.atTime(hora);
        System.out.println(dataEHora);

        ZonedDateTime dataComHoraETimeZone = dataEHora.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println(dataComHoraETimeZone);

        LocalDateTime semTimeZone = dataComHoraETimeZone.toLocalDateTime();
        System.out.println(semTimeZone);

        LocalDate dataDoPassado = LocalDate.now().withYear(1990);
        System.out.println(dataDoPassado.getYear());

        LocalDate amanha = LocalDate.now().plusDays(1);
        System.out.println(hoje.isBefore(amanha));
        System.out.println(hoje.isAfter(amanha));
        System.out.println(hoje.isEqual(amanha));

        ZonedDateTime tokyo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));

        System.out.println(tokyo.isEqual(saoPaulo));

        tokyo = tokyo.plusHours(12);
        System.out.println(tokyo.isEqual(saoPaulo));

        System.out.println("Hoje é dia: " + MonthDay.now().getDayOfMonth());

        LocalDate data = LocalDate.now();
        YearMonth ym = YearMonth.from(data);
        System.out.println(ym.getMonth() + " " + ym.getYear());

        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(1));

        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

        // Formatação com a nova API;
        System.out.println("------------------");
        System.out.println("Formatações");
        String format = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(format);

        System.out.println(agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Transformando String em representação de data;
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = agora.format(formatador);
        LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
        System.out.println(agoraEmData);

        // Período
        LocalDate outraData = LocalDate.of(2000, 02, 20);
        long dias = ChronoUnit.DAYS.between(outraData, agora);
        long meses = ChronoUnit.MONTHS.between(outraData, agora);
        long anos = ChronoUnit.YEARS.between(outraData, agora);
        System.out.printf("%s dias, %s meses e %s anos", dias, meses, anos);

        Period periodo = Period.between(outraData, agoraEmData);
        System.out.printf("\n%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());

        // Duração
        LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
        Duration duration = Duration.between(agora, daquiAUmaHora);

        if (duration.isNegative()) {
            duration = duration.negated();
        }
        System.out.printf("\n%s horas, %s minutos e %s segundos",
                duration.toHours(), duration.toMinutes(), duration.getSeconds());
    }
}
