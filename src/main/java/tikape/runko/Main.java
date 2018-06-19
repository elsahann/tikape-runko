package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:smoothiearkisto.db");

        AnnosDao annosDao = new AnnosDao(database);
        RaakaAineDao raakaAineDao = new RaakaAineDao(database);
        AnnosRaakaAineDao annosRaakaAineDao = new AnnosRaakaAineDao(database);
        
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());
            
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAineet", raakaAineDao.findAll());
            
            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());
        
        post("/ainekset", (req, res) -> {
            RaakaAine ra = new RaakaAine(-1, req.queryParams("Raaka-aineen nimi"));
            raakaAineDao.saveOrUpdate(ra);

            res.redirect("/ainekset");
            return "";
        });
        
        get("/annokset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());
            map.put("raakaAineet", raakaAineDao.findAll());
            
            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        post("/annokset", (req, res) -> {
            Annos a = new Annos(-1, req.queryParams("Annoksen nimi"));
            annosDao.saveOrUpdate(a);
            res.redirect("/annokset");
            return "";
        });
        
        get("/annokset/:id", (req, res) -> {
            Integer annosId = Integer.parseInt(req.params(":id"));
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());
            map.put("raakaAineet", raakaAineDao.findAll());
            
            return new ModelAndView(map, "annokset");
        }, new ThymeleafTemplateEngine());
        
        post("/annokset/:id", (req, res) -> {
            Integer annosId = Integer.parseInt(req.params(":id"));
            Integer raakaAineId = Integer.parseInt(req.queryParams("raakaAineId"));
            Integer jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            Integer maara = Integer.parseInt(req.queryParams("maara"));
            String ohje = req.queryParams("ohje");
  
            AnnosRaakaAine ara = new AnnosRaakaAine(-1, annosId, raakaAineId, jarjestys, maara, ohje);
            annosRaakaAineDao.saveOrUpdate(ara);
            
            res.redirect("/annokset");
            return "";
        });
        

        /*get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("opiskelijat", opiskelijaDao.findAll());

            return new ModelAndView(map, "opiskelijat");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine()); */ 
    }
}
