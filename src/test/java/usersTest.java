import aman.fr.persistantdata.modele.user.Librarian;
import aman.fr.persistantdata.modele.user.User;
import mediatek2021.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class usersTest {
    private static Utilisateur librarian;
    private static Utilisateur subscriber;

    @BeforeEach
    public static void init(){
        subscriber = new User("non-admin@exemple.fr", "nonadmin" );
        librarian = new Librarian("admin@exemple.fr", "admin" );
    }

    @Test
    public static void main(String[] args){
        init();
        assertTrue(subscriber instanceof User);
        assertTrue(librarian instanceof Librarian);

        assertEquals("non-admin@exemple.fr", subscriber.data()[1]);
        assertEquals("admin@exemple.fr", librarian.data()[1]);
    }
}
