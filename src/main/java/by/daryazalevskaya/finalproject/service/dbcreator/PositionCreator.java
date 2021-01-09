package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Position;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionCreator extends Creator<Position> {
    @Override
    public Position createEntity(ResultSet set) throws SQLException {
        return Position.builder()
                .name(set.getString("name"))
                .id(set.getInt("id"))
                .build();
    }
}
