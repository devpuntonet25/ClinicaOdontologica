package dao.impl;


import dao.IDao;
import model.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PatientDaoH2 implements IDao<Patient> {

    private static final String SQL_CREATE_TABLE_PATIENT = """
            DROP TABLE IF EXISTS PATIENT;
            CREATE TABLE PATIENT(
            ID INT AUTO_INCREMENT PRIMARY KEY,
            FIRSTNAME VARCHAR(120) NOT NULL,
            LASTNAME VARCHAR(120) NOT NULL,
            CARDIDENTITY VARCHAR(120) NOT NULL,
            ADMISSIONOFDATE DATE NOT NULL
            );
            """;

    public PatientDaoH2() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_PATIENT);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ClinicaMaven", "sa", "sa");
    }
    @Override
    public Patient save(Patient patient) {
        return null;
    }

    @Override
    public Patient findById(Integer id) {
        return null;
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Patient> findAll() {
        return null;
    }
}
