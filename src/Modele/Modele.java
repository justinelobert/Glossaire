package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Agnes
 */
public class Modele {

    /**
     * Connexion à la base de donnée NESTI
     *
     * @return (connexion)
     */
    public static Connection startConnection() {

        /* Connexion à la base de données */
        String url = "jdbc:mysql://127.0.0.1/glossaire";
        String utilisateur = "root";
        String motDePasse = "";
        Connection co = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            co = DriverManager.getConnection(url, utilisateur, motDePasse);
            if (!co.isClosed()) {
                System.out.println(
                        "Connexion au serveur... OK"
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return co;

    }

    /**
     * *
     * Fermeture de la base de donnée. 
     * @param connexion *
     */
    public static void closeConnection(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.err.println("Erreur fermreture: " + e.getMessage());
            }
        }
    }
}
