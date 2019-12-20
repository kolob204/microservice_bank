package com.microservice.dbholder.resource;

import com.microservice.dbholder.entity.Rule;
import com.microservice.dbholder.entity.SourceOfTransaction;
import com.microservice.dbholder.entity.TypeOfTransaction;
import com.microservice.dbholder.repository.RuleRepo;
import com.microservice.dbholder.repository.SourceRepo;
import com.microservice.dbholder.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Controller
public class RulesResource {

   @Autowired
    private RuleRepo ruleRepo;

   @Autowired
   private SourceRepo sourceRepo;

   @Autowired
   private TypeRepo typeRepo;



    //@RequestMapping("/hello")
    //@GetMapping(value = {"/hello"})
    @GetMapping("/hello")
    public String simpleCheck() {

    return "hello";
    }

    //@RequestMapping(value = "/getRules", method = RequestMethod.GET, produces = {"application/json"})   - ok
    @RequestMapping("/getRules")
    public List<Rule> getRules() {

        List<Rule> all_rules = ruleRepo.findAll();

    return all_rules;
    }

    @RequestMapping("/getSources")
    public List<SourceOfTransaction> getSources() {

     List<SourceOfTransaction> all_sources = sourceRepo.findAll();

    return all_sources;
    }

    @RequestMapping("/getTypes")
    public List<TypeOfTransaction> getTypes() {

      List<TypeOfTransaction> all_types = typeRepo.findAll();
    return all_types;
    }


}
