/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

/**
 *
 * @author elsa
 */
public class AnnosRaakaAine {
    private Integer id;
    private Integer annosId;
    private Integer raakaAineId;
    private Integer jarjestys;
    private Integer maara;
    private String ohje;
    
    public AnnosRaakaAine(Integer id, Integer annosId, Integer raakaAineId, Integer jarjestys, Integer maara, String ohje){
        this.id = id;
        this.annosId = annosId;
        this.raakaAineId = raakaAineId;
        this.jarjestys=jarjestys;
        this.maara=maara;
        this.ohje = ohje;
    }
    
    public int getId(){
        return this.id;
    }
   
    
    public int getAnnosId(){
        return this.annosId;
    }
    
    
    public int getRaakaAineId(){
        return this.raakaAineId;
    }
    
    
    public int getJarjestys(){
        return this.jarjestys;
    }
    
    
    public int getMaara(){
        return this.maara;
    }
    
    
    public String getOhje(){
        return this.ohje;
    }
  
}
