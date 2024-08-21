package service;



import dao.IDao;
import model.Dentist;

import java.util.List;

public class DentistService {
    private IDao<Dentist> dentistIDao;

    public DentistService(IDao<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist dentist) {
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id) {
        return dentistIDao.findById(id);
    }

    public void update(Dentist dentist) {
        dentistIDao.update(dentist);
    }

    public void deleteById(Integer id) {
        dentistIDao.deleteById(id);
    }

    public List<Dentist> findAll() {
        return dentistIDao.findAll();
    }
}
