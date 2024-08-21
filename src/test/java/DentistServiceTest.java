

import dao.impl.DentistDaoH2;
import model.Dentist;
import org.junit.jupiter.api.Test;
import service.DentistService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DentistServiceTest {

    private DentistService dentistService = new DentistService(new DentistDaoH2());

    @Test
    void save() {
        Dentist dentist = new Dentist();
        dentist.setRegistration(4568);
        dentist.setFirstName("Manuela");
        dentist.setLastName("Yepes");

        dentistService.save(dentist);

        //Si luego de guardar, tenemos id en el odontólogo, es porque se guardó sin problemas y se generó el id en la BD
        assertNotNull(dentist.getId());

    }

    @Test
    void findById() {
        Dentist dentist = new Dentist(4568, "Manuela", "Yepes");
        dentistService.save(dentist);

        //Sabiendo que en la BD hay un odontólogo con el id 2, si no es nulo, es porque está funcionando sin problema
        assertNotNull(dentistService.findById(dentist.getId()));

    }

    @Test
    void update() {
        //Creamos un odontólogo y lo guardamos
        Dentist dentist = new Dentist(4568, "Manuela", "Yepes");
        dentistService.save(dentist);

        //Sabemos que es el primer registro, entonces su ID será 1, por tanto creamos el objeto para actualizar
        Dentist dentistToUpdate = new Dentist(1,4568, "Martina", "Gonzales");
        dentistService.update(dentistToUpdate);

        //Obtenemos el odontólogo que supuestamente se actualizó
        Dentist updatedDentist = dentistService.findById(1);


        //Ahora comparamos que se haya realizado la actualización
        assertEquals(true, updatedDentist.getFirstName().equals(dentistToUpdate.getFirstName()));


    }

    @Test
    void deleteById() {
        //Creamos un odontólogo y lo guardamos
        Dentist dentist = new Dentist(4568, "Manuela", "Yepes");
        dentistService.save(dentist);

        //Como es el primer odontólogo que se guarda, sabemos que si id es el 1
        dentistService.deleteById(1);

        //Ahora, si no hay un registro con el id 1, todo está funcionando de forma correcta
        assertNull(dentistService.findById(1));
    }

    @Test
    void findAll() {
        //Creamos y guardamos un conjunto de odontólogos
        List<Dentist> dentistList = new ArrayList<>();
        dentistList.add(new Dentist(2058, "Carlos", "García"));
        dentistList.add(new Dentist(2244, "Martina", "Perez"));
        dentistList.add(new Dentist(3025, "Maria", "Lopera"));
        dentistService.save(dentistList.get(0));
        dentistService.save(dentistList.get(1));
        dentistService.save(dentistList.get(2));

        //Miramos que efectivamente el findAll me esté retornando algo
        assertTrue(dentistService.findAll().size() > 0);
    }
}