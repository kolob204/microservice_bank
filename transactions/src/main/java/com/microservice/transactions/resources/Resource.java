package com.microservice.transactions.resources;

import com.microservice.transactions.entity.Rule;

import com.microservice.transactions.entity.SourceOfTransaction;
import com.microservice.transactions.entity.TypeOfTransaction;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class Resource {

    @GetMapping("/")
    public String index(  Map<String,Object> model) {
        //model.put("key","lets do it!");
        RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate2 = new RestTemplate();

        ResponseEntity<List<TypeOfTransaction>> response = restTemplate.exchange(
                "http://localhost:8081/getTypes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TypeOfTransaction>>(){});
        List<TypeOfTransaction> types = response.getBody();

        ResponseEntity<List<SourceOfTransaction>> response2 = restTemplate.exchange(
                "http://localhost:8081/getSources",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SourceOfTransaction>>(){});
        List<SourceOfTransaction> sources = response2.getBody();

        model.put("types",types);
        model.put("sources",sources);

        model.put("curtime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis())));
        return "index";
    }


    //@GetMapping("/doTransaction")
    @PostMapping("/doTransaction")
    public String doTransaction(@RequestParam String type,
                              @RequestParam String direction,
                              @RequestParam String source,
                              @RequestParam String summ,
                              @RequestParam String currency,
                              @RequestParam String date,
                                Map<String,Object> model) {

        RestTemplate restTemplate = new RestTemplate();
        //Проверяем что данные вообще ввели

             //List<Rule> rules = ...;

            //RuleList responseRuleList  = restTemplate.getForObject("http://localhost:8081/getRules",RuleList.class);
            //List<Rule> ruleList = responseRuleList.getRules();

            //Rule[] responseRuleList  = restTemplate.getForObject("http://localhost:8081/getRules",Rule[].class);
        if (!type.equals("") && !source.equals("") && !summ.equals("") && !date.equals("")) {
            ResponseEntity<List<Rule>> response = restTemplate.exchange(
                    "http://localhost:8081/getRules",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Rule>>(){});
            List<Rule> rules = response.getBody();

            int id_in_list=-1;
            Timestamp cur_date = new Timestamp(System.currentTimeMillis());

            //
            //   NullPointer Execption - нельзя в одной проверке использовать вместе проверку " !=null "  and  .equal

            // недостаток этого поиска в том, что
            // поиск не выделяет какое найденное правило будет приоритетней:
            // с полями - которые совпали с задаными или с просто с пустыми, не задаными полями.
            List<int[]> toSort = new ArrayList<>();

            for(int i=0;i<rules.size();i++) {
                int count=0,null_counter=0;
                int[] mass = new int[]{0,0,0,0,0,0,0,0,0}; // Матрица сравнения
                if ( rules.get(i).getType()!=null ) {if ( rules.get(i).getType().equals(type) ) {count++; mass[0]=1;} }
                    else {null_counter++; mass[4]=1;}
                if ( rules.get(i).getDirection()!=null) {if (  rules.get(i).getDirection().equals(direction)) {count++; mass[1]=1;}}
                    else {null_counter++;mass[5]=1;}
                if ( rules.get(i).getSource()!=null) {if (  rules.get(i).getSource().equals(source) ) {count++; mass[2]=1;}}
                    else {null_counter++;mass[6]=1;}

                //обработчик суммы немного замудрёнее, чем сравнее других параметров
                if ( rules.get(i).getSumm()!=null ) {      // если сумма вообще задана в правиле - смотрим дальше
                    String temp_var=rules.get(i).getSumm_compare();
                    if (temp_var != null) // если в БД задан знак сравнения, значит работаем относительно него
                    {
                        // определяем какой знак сравнения задан
                        switch (temp_var) {
                            case "=": if (rules.get(i).getSumm().equals(summ)) { count++; mass[3] = 1;}
                            case ">": if (Integer.parseInt(summ) > Integer.parseInt(rules.get(i).getSumm()) ) { count++; mass[3] = 1;}
                            case "<": if (Integer.parseInt(summ) < Integer.parseInt(rules.get(i).getSumm()) ) { count++; mass[3] = 1;}
                        }
                    } else {
                        if (rules.get(i).getSumm().equals(summ))  // если не задан - считаем по умолчанию, что имеется ввиду  " = "
                        {                                          // и если сумма в правиле совпадает с суммо в транзакции, то помечаем поле как совпавшее
                            count++;
                            mass[3] = 1;
                        }
                    }
                } else {null_counter++; mass[7]=1;}  // если значение сумы вообще не задано, то помечаем поле как "Возможно" подходящее

                if (count==4) {    // Bесли нашлось правило, подходящее под заданные параметры, то оно становится ведущим
                    if ((rules.get(i).getEnd_Date().after(cur_date))&&(id_in_list==-1) ) {id_in_list = i;} //если правило ещё действующее, то помечаем его как выбранное на данный момент цикла
                                                                                 // Дополнительно найденное правило удовлетворяет условию
                                                                                    // "выбирается то правило, которое было создано позднее."
                                                                                    // потому что чем дальше по списку оно стоит, значит тем позднее оно было создано
                    else if ((rules.get(i).getEnd_Date().after(cur_date))&&(id_in_list!=-1) && (rules.get(i).getId()>rules.get(id_in_list).getId()) ) {id_in_list = i;}
                } else if (count+null_counter==4) {
                    //формируется обработчик массива выбора среди тех правил, что частично попадают по совпадению, а частично по не заданному полю
                    //учитывая, что правило ещё актуально по дате
                    if (rules.get(i).getEnd_Date().after(cur_date))
                        {mass[8]=i; toSort.add(mass); }
                }
            }

            if ( id_in_list==-1) {
                //если не нашли полноценного правила, то ищем среди тех, которые заданы не до конца
                if (toSort.size()>0) {
                    int max=0,id=-1;
                    Rule listelement=null;  //test change
                    for (int i=0;i<toSort.size();i++)
                    {
                       int sum = toSort.get(i)[0]+toSort.get(i)[1]+toSort.get(i)[2]+toSort.get(i)[3];
                       if (sum>max) {
                                        max=sum;
                                        id= (int) rules.get(toSort.get(i)[8]).getId();
                                        listelement=rules.get(toSort.get(i)[8]);  //test change

                                    }
                       else if (sum==max) {
                                            if (rules.get(toSort.get(i)[8]).getId()>id)
                                              {id= (int) rules.get(toSort.get(i)[8]).getId();
                                                  listelement=rules.get(toSort.get(i)[8]); //test change
                                              }
                                          }
                    }
                    if (id!=-1) {
                        model.put("id",id);
                        model.put("listelement",listelement); //test change
                        return "transaction";
                    }
                    else {
                        model.put("text1","В Базе данных нет Правил, удовлетворящих текущей транзакции");
                        return "error";
                    }
                }
                else {
                    model.put("text1","В Базе данных нет Правил, удовлетворящих текущей транзакции");
                    return "error";
                }


            } else {
                model.put("id",rules.get(id_in_list).getId());
                model.put("listelement",rules.get(id_in_list)); //test change

                if (rules.get(id_in_list).getAction()=="provide") {model.put("action","Транзакция проведена");}
                else {model.put("action","Транзакция отменена");}

                return "transaction";
            }
        } else {
                    model.put("text1","Введите все данные для обработки транзакции. \n Правило может иметь не заданные значения, а сама транзакция должна содержать все входные данные");
                    //return "redirect:/error"; - model сюда не передаётся
                    return "error";
                }
    }


 /*   @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public List<Rule> getRules(@PathVariable("id") long id) throws ParseException {
*//*        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date =  dateFormat.parse("2019-12-13");
        long time = date.getTime();
        new Timestamp(time);*//*

        return Collections.singletonList(

                new Rule(id,true,5,600,true,4,"2019-12-13","2019:12:24")
        );*/


}
