//package com.miu.pension;

/**
 * Hello world!
 *
 */

/*  public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
*/
package com.miu.pension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import com.miu.pension.*;

public class Main {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        loadSampleData();
        printAllEmployees();
        printQuarterlyUpcomingEnrollees();
    }

    private static void loadSampleData() {
        employees.add(new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105_945.50,
                new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00)));
        employees.add(new Employee(2, "Benard", "Shaw", LocalDate.of(2022, 9, 3), 197_750.00, null));
        employees.add(new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842_000.75,
                new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50)));
        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2022, 7, 21), 74_500.00, null));
        employees.add(new Employee(5, "Anna", "Wiltord", LocalDate.of(2022, 6, 15), 85_750.00, null));
        employees.add(new Employee(6, "Yosef", "Tesfalem", LocalDate.of(2022, 10, 31), 100_000.00, null));
    }

    private static void printAllEmployees() throws Exception {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println("All Employees (JSON):");
        printAsJson(sorted);
    }

    private static void printQuarterlyUpcomingEnrollees() throws Exception {
        LocalDate now = LocalDate.now();
        Month nextQuarterStart = now.getMonth().plus(3 - (now.getMonthValue() - 1) % 3);
        LocalDate start = LocalDate.of(now.getYear(), nextQuarterStart, 1);
        LocalDate end = start.plusMonths(3).minusDays(1);
        LocalDate threeYearsAgo = start.minusYears(3);

        List<Employee> upcoming = employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> !e.getEmploymentDate().isAfter(threeYearsAgo))
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        System.out.println("\nQuarterly Upcoming Enrollees (JSON):");
        printAsJson(upcoming);
    }

    /*
     * private static void printAsJson(Object obj) throws Exception {
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.enable(SerializationFeature.INDENT_OUTPUT);
     * System.out.println(mapper.writeValueAsString(obj));
     * }
     */

    private static void printAsJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // ✅ this is what enables LocalDate
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // optional: nicer format
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(obj));
    }

}
