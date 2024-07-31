package com.practice.jobApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String jobDescription;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private Long expInYears;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getExpInYears() {
        return expInYears;
    }

    public void setExpInYears(Long expInYears) {
        this.expInYears = expInYears;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
