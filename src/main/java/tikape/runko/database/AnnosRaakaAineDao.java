/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.AnnosRaakaAine;

public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {

    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        Integer annosId = rs.getInt("annos_id");
        Integer raakaAineId = rs.getInt("raaka_aine_id");
        Integer jarjestys = rs.getInt("jarjestys");
        Integer maara = rs.getInt("maara");
        String ohje = rs.getString("ohje");

        AnnosRaakaAine o = new AnnosRaakaAine(id, annosId, raakaAineId, jarjestys, maara, ohje);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> annosRaakaAineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer annosId = rs.getInt("annos_id");
            Integer raakaAineId = rs.getInt("raaka_aine_id");
            Integer jarjestys = rs.getInt("jarjestys");
            Integer maara = rs.getInt("maara");
            String ohje = rs.getString("ohje");

            annosRaakaAineet.add(new AnnosRaakaAine(id, annosId, raakaAineId, jarjestys, maara, ohje));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annosRaakaAineet;
    }
    
    public AnnosRaakaAine saveOrUpdate(AnnosRaakaAine object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO AnnosRaakaAine ( annos_id,raaka_aine_id, jarjestys, maara, ohje) VALUES (raaka_aine_id, annos_id, jarjestys, maara, ohje)");
            stmt.setInt(1, object.getAnnosId());
            stmt.setInt(2, object.getRaakaAineId());
            stmt.setInt(3, object.getJarjestys());
            stmt.setInt(4, object.getMaara());
            stmt.setString(5, object.getOhje());
            stmt.executeUpdate();
        }

        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
