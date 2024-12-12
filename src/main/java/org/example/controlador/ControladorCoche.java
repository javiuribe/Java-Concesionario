package org.example.controlador;

import org.example.controlador.dao.CocheDao;
import org.example.controlador.dao.impl.JpaCocheDao;
import org.example.modelo.Coche;

import java.sql.SQLException;

public class ControladorCoche {
    private CocheDao cocheDao;

    public ControladorCoche() {
        this.cocheDao = new JpaCocheDao();
    }

    public void guardarCoche(Coche coche) throws SQLException {
        Coche cocheExistente = cocheDao.findByMatricula(coche.getMatricula());
        if (cocheExistente != null) {
            throw new IllegalArgumentException("El coche con matrícula " + coche.getMatricula() + " ya existe.");
        }
        cocheDao.save(coche);
    }

    public Coche buscarPorMatricula(String matricula) throws SQLException {
        return cocheDao.findByMatricula(matricula);
    }

    public void actualizarCoche(Coche coche) throws SQLException {
        cocheDao.update(coche);
    }

    public void eliminarCoche(Coche coche) throws SQLException {
        cocheDao.delete(coche);
    }

}
