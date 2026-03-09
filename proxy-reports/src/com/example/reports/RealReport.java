package com.example.reports;

public class RealReport implements Report {
    private final String reportId;
    private final String title;
    private final String classification;
    private final String data;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.data = loadFromDisk(); 
    }

    @Override
    public void display(User user) {
        System.out.println("------------------------------------------");
        System.out.println("Displaying Report: [" + reportId + "]");
        System.out.println("Title: " + title);
        System.out.println("Security Level: " + classification);
        System.out.println("Access Granted to: " + user.getName());
        System.out.println("CONTENT: " + data);
        System.out.println("------------------------------------------");
    }

    private String loadFromDisk() {
        System.out.println("[DISK I/O] Accessing secure storage for " + reportId + "...");
        try { 
            Thread.sleep(1500);
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        }
        return "Confidential data stream for " + title + ". Reference ID: " + System.nanoTime();
    }
}