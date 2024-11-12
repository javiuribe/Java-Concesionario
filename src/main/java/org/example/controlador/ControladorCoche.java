package org.example.controlador;

import org.example.controlador.dao.CocheDao;
import org.example.controlador.dao.impl.JdbcCocheDao;
import org.example.modelo.Coche;

import java.sql.SQLException;

public class ControladorCoche {
    private CocheDao cocheDao;

    public ControladorCoche() throws SQLException {
        this.cocheDao = new JdbcCocheDao();
    }

    public void agregarCoche(Coche coche) throws SQLException {
        Coche cocheExistente = cocheDao.findByMatricula(coche.getMatricula());
        if (cocheExistente != null) {
            throw new IllegalArgumentException("El coche con matrícula " + coche.getMatricula() + " ya existe.");
        }
        cocheDao.save(coche);
    }

    public Coche leerCoche(String dni) throws SQLException {
        return cocheDao.findByMatricula(dni);
    }

    public void updateCoche(Coche coche) throws SQLException {
        cocheDao.update(coche);
    }

    public void deleteCoche(Coche coche) throws SQLException {
        cocheDao.delete(coche);
    }

}
