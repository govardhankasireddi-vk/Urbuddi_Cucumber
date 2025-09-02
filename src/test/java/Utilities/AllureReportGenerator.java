package Utilities;

import java.io.IOException;

public class AllureReportGenerator {

    public static void generateAllureReport() {

        try {

            String command = "\"C:\\Users\\Admin\\Downloads\\allure-commandline-2.13.0\\allure-2.13.0\\bin\\allure.bat\" generate C:\\Users\\Admin\\IdeaProjects\\untitled\\allure-results  --clean -o C:\\Users\\Admin\\IdeaProjects\\untitled\\allure-report";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Allure report generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
