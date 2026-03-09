package com.example.reports;

public class ReportProxy implements Report {
    private final String reportId;
    private final String title;
    private final String classification;
    
    private RealReport realReport;
    private final AccessControl accessControl = new AccessControl();

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: User " + user.getName() + 
                               " (Role: " + user.getRole() + ") cannot view " + 
                               classification + " reports.");
            return;
        }

        if (realReport == null) {
            System.out.println("[proxy] First access authorized. Initializing real report...");
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] Reusing already loaded report for " + reportId);
        }

        realReport.display(user);
    }
}