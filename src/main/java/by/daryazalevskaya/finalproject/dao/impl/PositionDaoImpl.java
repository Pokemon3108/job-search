package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.PositionDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.model.Position;
import by.daryazalevskaya.finalproject.service.creator.PositionCreator;

import java.util.List;
import java.util.Optional;

public class PositionDaoImpl extends BaseDao implements PositionDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM desired_position_type";
    private static final String READ_QUERY = "SELECT * FROM desired_position_type WHERE id =?";

    @Override
    public Integer create(Position entity) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public Optional<Position> read(int id) throws DaoException {
        final String fieldName = "name";
        String posName = findStringFieldById(id, READ_QUERY, fieldName);
        Position position = new Position(id, posName);
        return Optional.ofNullable(position);
    }

    @Override
    public void update(Position entity) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public void delete(int id) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public List<Position> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new PositionCreator());
    }
}
