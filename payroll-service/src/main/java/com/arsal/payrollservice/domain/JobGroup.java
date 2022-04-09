package com.arsal.payrollservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "job_group")
public class JobGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rate", nullable = false)
    private Double rate;

    public JobGroup() {
    }

    public JobGroup(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
