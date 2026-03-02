package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class IncidentTicket {
    // 1. All fields are private and final
    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags; // Immutable collection
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    // 2. Private constructor: only the Builder can call this
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // 3. Defensive copy + Unmodifiable wrap to prevent external mutation
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    // Getters only (No Setters)
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } 
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    // 4. toBuilder() allows "updating" by creating a new instance based on this one
    public Builder toBuilder() {
        return new Builder()
                .id(id).reporterEmail(reporterEmail).title(title)
                .description(description).priority(priority)
                .tags(tags).assigneeEmail(assigneeEmail)
                .customerVisible(customerVisible).slaMinutes(slaMinutes).source(source);
    }

    public static Builder builder() { return new Builder(); }

    // --- INNER BUILDER CLASS ---
    public static class Builder {
        private String id;
        private String reporterEmail;
        private String title;
        private String description;
        private String priority = "LOW"; // Default value
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible = true;
        private Integer slaMinutes;
        private String source = "CLI";

        public Builder id(String id) { this.id = id; return this; }
        public Builder reporterEmail(String email) { this.reporterEmail = email; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder description(String desc) { this.description = desc; return this; }
        public Builder priority(String p) { this.priority = p; return this; }
        public Builder tags(List<String> tags) { 
            this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>(); 
            return this; 
        }
        public Builder assigneeEmail(String email) { this.assigneeEmail = email; return this; }
        public Builder customerVisible(boolean v) { this.customerVisible = v; return this; }
        public Builder slaMinutes(Integer min) { this.slaMinutes = min; return this; }
        public Builder source(String s) { this.source = s; return this; }

        public IncidentTicket build() {
            // 5. Centralized Validation
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            if (assigneeEmail != null) Validation.requireEmail(assigneeEmail, "assigneeEmail");

            return new IncidentTicket(this);
        }
    }

    @Override
    public String toString() {
        return "IncidentTicket{id='" + id + "', title='" + title + "', priority='" + priority + "', tags=" + tags + ", assignee='" + assigneeEmail + "'}";
    }
}