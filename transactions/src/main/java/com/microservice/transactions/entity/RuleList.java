package com.microservice.transactions.entity;

import java.util.ArrayList;
import java.util.List;

public class RuleList {

    private List<Rule> rules;

    public RuleList() {
        rules = new ArrayList<>();
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
