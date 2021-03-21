import aman.fr.persistantdata.modele.docs.DocType;
import aman.fr.persistantdata.modele.docs.DocumentFactory;
import aman.fr.persistantdata.modele.statusdoc.Libre;
import mediatek2021.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class DocumentTest {
    private static Document book,cd,dvd;

    @BeforeAll
    public static void init(){
        book = DocumentFactory.newDocument("Steve Jobs - The Biografhy", DocType.BOOK,true, "Test_Title","Test_Desc");
        dvd = DocumentFactory.newDocument("Harry-Potter", DocType.DVD,true, "Test_Title","Test_Desc");
        cd = DocumentFactory.newDocument("Drake - More Life", DocType.CD,true, "Test_Title","Test_Desc");
    }

    public static void allDocsTest(){
        Assertions.assertEquals(Libre.class, book.data()[3].getClass());
        Assertions.assertEquals(Libre.class, dvd.data()[3].getClass());
        Assertions.assertEquals(Libre.class, cd.data()[3].getClass());
    }

    @Test
    public static void main(String[] args){
        init();
        allDocsTest();
    }
}
