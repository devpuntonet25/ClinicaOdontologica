

import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Dentist> dentistList = new ArrayList<>();
        dentistList.add(new Dentist(2058, "Carlos", "García"));
        dentistList.add(new Dentist(2244, "Martina", "Perez"));
        dentistList.add(new Dentist(3025, "Maria", "Lopera"));

        //Creamos un objeto del DentistService y le pasamos un objeto de la clase que implementa la interfaz IDao y de acuerdo a la
        //base de datos que queramos manejar
        DentistService dentistService = new DentistService(new DentistDaoH2());

        //Guardamos en la BD
        dentistService.save(dentistList.get(0));
        dentistService.save(dentistList.get(1));
        dentistService.save(dentistList.get(2));

        //Consultamos la BD recuperando los registros de odontólogos persistidos
        dentistService.findAll().forEach(System.out::println);

        //Busquemos un odontólogo por ID
        Integer id = 2;
        System.out.println(dentistService.findById(id));

        //Actualicemos el odontólogo con ID=3
        Dentist dentist = new Dentist(3, 3025, "Mariana", "Pajón");
        dentistService.update(dentist);


        //Eliminemos el odontólogo con ID 2
        dentistService.deleteById(2);

        //Miremos nuevamente cómo quedó la base de datos con la actualización y eliminado
        dentistService.findAll().forEach(System.out::println);
    }
}
