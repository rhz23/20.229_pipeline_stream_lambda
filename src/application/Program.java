package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the full file path: ");
        String path = sc.nextLine();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))){

            List<Employee> employeeList = new ArrayList<>();

            String line = bf.readLine();
            while (line != null){
                String[] fields = line.split(",");
                employeeList.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = bf.readLine();
            }

            System.out.println();
            System.out.print("Entrar com o valor do salario de corte: ");
            double valor = sc.nextDouble();

            List<String> emailList = employeeList.stream()
                    .filter(employee -> employee.getSalary() > valor)
                    .map(employee -> employee.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            emailList.forEach(System.out::println);

            double sum = employeeList.stream()
            .filter(employee -> employee.getName().charAt(0) == 'M')
            .map(employee -> employee.getSalary()).reduce(0.0,Double::sum);

            System.out.printf("A soma dos salarios dos funcionários que começam com M é: %.2f", sum );

        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
