package main.service.impl;

import main.entity.Degree;
import main.entity.Department;
import main.entity.Lector;
import main.repository.DegreeRepository;
import main.repository.DepartmentRepository;
import main.repository.LectorRepository;
import main.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private Scanner scanner = new Scanner(System.in);

    private DepartmentRepository departmentRepository;
    private DegreeRepository degreeRepository;
    private LectorRepository lectorRepository;

    @Override
    public void findHeadOfDepartment() {
        Department department = parseDepartment();
        Department resultDepartment = departmentRepository.findByDepartmentNameFetchLector(department.getDepartmentName());
        Optional<Lector> optionalLector = Optional.ofNullable(resultDepartment.getLector());

        System.out.println();
        System.out.println("Answer: Head of " + department.getDepartmentName() + "department is " + (optionalLector.isPresent() ? optionalLector.get().getNameSurname() : "nobody"));
        System.out.println();
    }


    @Override
    public void departmentStatistic() {
        Department department = parseDepartment();
        final List<Degree> allDegree = degreeRepository.findAll();
        System.out.print("Answer: ");
        for (Degree degree : allDegree) {
            final Long count = degreeRepository.countLectorsGroupByDegreeName(department.getId(), degree.getId());
            System.out.println(degree.getDegreeName() + " - " + count);
        }
        System.out.println();
    }

    @Override
    public void averageSalaryInDepartment() {
        final Department department = parseDepartment();
        final Double averageSalaryInDepartment = lectorRepository.findAverageSalaryInDepartment(department.getId());
        final Optional<Double> averageSalaryInDepartmentOptional = Optional.ofNullable(averageSalaryInDepartment);
        System.out.println();
        System.out.println("Answer: The average salary of " + department.getDepartmentName() + " is " + averageSalaryInDepartmentOptional.orElse(0.0));
        System.out.println();
    }

    @Override
    public void countOfEmployeeInDepartment() {
        final Department department = parseDepartment();
        final Integer result = lectorRepository.countLectorByDepartmentId(department.getId());

        System.out.println();
        System.out.println("Answer: " + result);
        System.out.println();
    }

    @Override
    public void findNameAndSurnameByExample() {
        System.out.println("Enter part of name");
        final String partOfName = scanner.next();
        List<Lector> result = new ArrayList<>();
        result.addAll(lectorRepository.findByNameSurnameStartingWith(partOfName));
        result.addAll(lectorRepository.findByNameSurnameContaining(partOfName));
        result.addAll(lectorRepository.findByNameSurnameEndingWith(partOfName));
        final String resultString = result.stream().distinct().map(Lector::getNameSurname).collect(Collectors.joining(", "));

        System.out.println("Answer: " + resultString);
        System.out.println();
    }

    @Override
    public int showMainMenu() {
        boolean notNumber = true;
        String number;
        do {
            System.out.println();
            menu();
            number = scanner.next();
            Pattern pattern = Pattern.compile("[0-5]");
            if (pattern.matcher(number).matches()) {
                notNumber = false;
            }
        } while (notNumber);

        return Integer.valueOf(number);
    }

    private Department parseDepartment() {
        boolean noSuchDepartment = true;
        Department department = null;
        do {
            askNameOfDepartment();
            String answer = scanner.next();
            if (answer.equals("0")) {
                showMainMenu();
            } else {
                department = departmentRepository.findByDepartmentName(answer);
                if (department == null) {
                    System.out.println("No such department");
                } else noSuchDepartment = false;
            }
        } while (noSuchDepartment);
        return department;
    }

    private void menu() {
        System.out.println("Enter number from 0 to 5");
        System.out.println("1. Who is head of department");
        System.out.println("2. Show department statistic ");
        System.out.println("3. Show the average salary for department ");
        System.out.println("4. Show count of employee for department");
        System.out.println("5. Global search by part of name");
        System.out.println("0. Exit");
    }


    private void askNameOfDepartment() {
        System.out.println("Enter name of department or enter " + 0 + " for returning in main menu");
    }

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    public void setDegreeRepository(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Autowired
    public void setLectorRepository(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }
}
