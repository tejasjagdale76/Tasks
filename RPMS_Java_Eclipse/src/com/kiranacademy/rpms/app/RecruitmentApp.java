package com.kiranacademy.rpms.app;

import java.util.Scanner;

import com.kiranacademy.rpms.dao.*;
import com.kiranacademy.rpms.model.Candidate;
import com.kiranacademy.rpms.service.*;

public class RecruitmentApp {

    public static void main(String[] args) {

        CandidateDAO candidateDAO = new CandidateDAOImpl();
        JobDAO jobDAO = new JobDAOImpl();
        ApplicationDAO applicationDAO = new ApplicationDAOImpl();

        CandidateService candidateService = new CandidateService(candidateDAO);
        EligibilityService eligibilityService = new EligibilityService();
        ApplicationService applicationService =
                new ApplicationService(candidateDAO, jobDAO, applicationDAO, eligibilityService);
        ReportService reportService = new ReportService();
        ActivityLogService logService = new ActivityLogService();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================================");
            System.out.println("RECRUITMENT & PLACEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. View Candidates");
            System.out.println("2. Add Candidate");
            System.out.println("3. View Open Jobs");
            System.out.println("4. Apply for Job");
            System.out.println("5. Candidate Technology Report");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        candidateService.getAllCandidates()
                                .forEach(System.out::println);
                        break;

                    case 2:
                        Candidate c = new Candidate();

                        System.out.print("Candidate Code: ");
                        c.setCandidateCode(sc.nextLine());

                        System.out.print("Full Name: ");
                        c.setFullName(sc.nextLine());

                        System.out.print("Email: ");
                        c.setEmail(sc.nextLine());

                        System.out.print("Mobile: ");
                        c.setMobile(sc.nextLine());

                        System.out.print("Qualification: ");
                        c.setQualification(sc.nextLine());

                        System.out.print("Passing Year: ");
                        c.setPassingYear(Integer.parseInt(sc.nextLine()));

                        System.out.print("Percentage: ");
                        c.setPercentage(Double.parseDouble(sc.nextLine()));

                        System.out.print("Backlog Count: ");
                        c.setBacklogCount(Integer.parseInt(sc.nextLine()));

                        System.out.print("Primary Technology: ");
                        c.setPrimaryTechnology(sc.nextLine());

                        c.setProfileStatus("ACTIVE");
                        c.setPlacementStatus("NOT_PLACED");

                        int id = candidateService.addCandidate(c);
                        System.out.println("Candidate created. ID = " + id);
                        logService.log("Candidate created: " + c.getCandidateCode());
                        break;

                    case 3:
                        jobDAO.findOpenJobs().forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Candidate ID: ");
                        int candidateId = Integer.parseInt(sc.nextLine());

                        System.out.print("Job ID: ");
                        int jobId = Integer.parseInt(sc.nextLine());

                        int appId = applicationService.applyForJob(candidateId, jobId);
                        System.out.println("Application created. ID = " + appId);
                        logService.log("Application created: candidate=" + candidateId + ", job=" + jobId);
                        break;

                    case 5:
                        reportService.candidateCountByTechnology()
                                .forEach((technology, count) ->
                                        System.out.println(technology + " : " + count));
                        break;

                    case 6:
                        System.out.println("Application closed.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
