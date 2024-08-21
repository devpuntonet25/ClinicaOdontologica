package dao.impl;


import dao.IDao;
import model.Dentist;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoH2 implements IDao<Dentist> {

    private static final String SQL_CREATE_TABLE_DENTIST = """
            DROP TABLE IF EXISTS DENTIST;
            CREATE TABLE DENTIST(
                ID INT AUTO_INCREMENT PRIMARY KEY,
                REGISTRATION INT NOT NULL,
                FIRSTNAME VARCHAR(120) NOT NULL,
                LASTNAME VARCHAR(120) NOT NULL
            );
            """;

    private static final String SQL_INSERT = """
            INSERT INTO DENTIST (REGISTRATION, FIRSTNAME, LASTNAME) VALUES(?, ?, ?);
            """;

    private static final String SQL_FIND_BY_ID = """
            SELECT * FROM DENTIST WHERE ID = ?;
            """;

    private static final String SQL_UPDATE = """
            UPDATE DENTIST SET REGISTRATION = ?, FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?;
            """;

    private static final String SQL_DELETE_BY_ID = """
            DELETE FROM DENTIST WHERE ID = ?;
            """;

    private static final String SQL_SELECT_ALL = """
            SELECT * FROM DENTIST;
            """;
    private static final Logger LOGGER = Logger.getLogger(DentistDaoH2.class);

    public DentistDaoH2() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE_DENTIST);
            LOGGER.info(SQL_CREATE_TABLE_DENTIST);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/ClinicaMaven", "sa", "sa");
    }

    @Override
    public Dentist save(Dentist dentist) {
        Connection connection = null;

        try {
            connection = getConnection();
            LOGGER.info("Iniciando el guardado de un odontólogo");
            //Preparamos el statement y retenemos la clave o ID generada
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, dentist.getRegistration());
            psInsert.setString(2, dentist.getFirstName());
            psInsert.setString(3, dentist.getLastName());
            psInsert.execute();

            //Obtenemos el ResultSet del registro que se acabó de insertar, ya que este tiene el ID autogenerado
            ResultSet resultSet = psInsert.getGeneratedKeys();
            if (resultSet.next()) {
                dentist.setId(resultSet.getInt("ID"));
                LOGGER.info("Se registró el odontólogo: " + dentist.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

        return dentist;
    }

    @Override
    public Dentist findById(Integer id) {
        Connection connection = null;
        LOGGER.info("Iniciando proceso para obtener un odontólogo por ID");
        Dentist dentist = null;

        try {
            connection = getConnection();
            PreparedStatement psFindById = connection.prepareStatement(SQL_FIND_BY_ID);
            psFindById.setInt(1, id);
            ResultSet resultSet = psFindById.executeQuery();

            if (resultSet.next()) {
                dentist = new Dentist(resultSet.getInt("ID"), resultSet.getInt("REGISTRATION"),
                        resultSet.getString("FIRSTNAME"), resultSet.getString("LASTNAME"));
                LOGGER.info("Se obtuvo de la BD el odontólogo: " + dentist);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                LOGGER.error(sqlException.getMessage());
                sqlException.printStackTrace();
            }
        }

        return dentist;
    }

    @Override
    public void update(Dentist dentist) {
        LOGGER.info("Iniciando el proceso de actualización de un odontólogo");
        Connection connection = null;

        try {
            connection = getConnection();

            //Inicio de la transacción
            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, dentist.getRegistration());
            psUpdate.setString(2, dentist.getFirstName());
            psUpdate.setString(3, dentist.getLastName());
            psUpdate.setInt(4, dentist.getId());

            psUpdate.execute();

            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Se actualizó el odontólogo con ID: " + dentist.getId() + ", y quedó así: " + dentist);

        } catch(Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage());
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        LOGGER.info("Iniciando proceso de eliminación de un odontólogo");
        Connection connection = null;

        try {
            connection = getConnection();

            //Inicio de la transacción
            connection.setAutoCommit(false);

            PreparedStatement psDeleteById = connection.prepareStatement(SQL_DELETE_BY_ID);
            psDeleteById.setInt(1, id);
            psDeleteById.execute();

            //Fin de la transacción
            connection.commit();
            connection.setAutoCommit(true);

            LOGGER.info("Se borró el odontólogo con ID " + id);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch(SQLException sqlException) {
                LOGGER.error(sqlException.getMessage());
                sqlException.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                LOGGER.error(sqlException.getMessage());
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public List<Dentist> findAll() {
        LOGGER.info("Iniciando proceso para obtener todos los odontólogos");
        List<Dentist> dentistList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            LOGGER.info(SQL_SELECT_ALL);
            while (resultSet.next()) {
                dentistList.add(new Dentist(
                        resultSet.getInt("ID"),
                        resultSet.getInt("REGISTRATION"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME")
                ));
            }

        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return dentistList;
    }
}
