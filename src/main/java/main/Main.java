package main;

import main.service.MenuService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
        final MenuService menuService = context.getBean(MenuService.class);

        while (isRunning) {
            switch (menuService.showMainMenu()){
                case 1:{
                    menuService.findHeadOfDepartment();
                    break;
                }
                case 2:{
                    menuService.departmentStatistic();
                    break;
                }
                case 3:{
                    menuService.averageSalaryInDepartment();
                    break;
                }
                case 4:{
                    menuService.countOfEmployeeInDepartment();
                    break;
                }
                case 5:{
                    menuService.findNameAndSurnameByExample();
                    break;
                }
                case 0:{
                    isRunning = false;
                    break;
                }
                default: break;
            }
        }
    }
}
