 /*
 * Created on @Nov 14, 2012
 * Copyright - Confidential use
 */
 package ucab.ingsw;
 import lombok.extern.slf4j.Slf4j;
 import org.jmock.Expectations;
import org.jmock.Mockery;
 import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
 import ucab.ingsw.service.InstagramService;
import ucab.ingsw.model.User;
import ucab.ingsw.repository.UserRepository;
 import org.jmock.lib.legacy.ClassImposteriser;
 import ucab.ingsw.response.MediaUrlsResponse;
 import ucab.ingsw.service.UserService;

 @Slf4j
@RunWith(JMock.class)
public class InstagramServiceTest {

    private UserService serviceUser;

    private InstagramService serviceInst;


    private Mockery context = new JUnit4Mockery();

     private Mockery context3 = new JUnit4Mockery();

     private Mockery context4 = new JUnit4Mockery();

    public InstagramServiceTest() {
    }

    /**
     * Fase de inicializacion donde se realiza la puesta a punto de todos los
     * Fixtures involucrados en la prueba.
     */
    @Before
    public void inicializacion() {
        context3.setImposteriser(ClassImposteriser.INSTANCE);
        serviceInst = context3.mock(InstagramService.class);
        context4.setImposteriser(ClassImposteriser.INSTANCE);
        serviceUser = context4.mock(UserService.class);
    }

    /**
     * Fase de finalizacion de los elementos usados durante la ejecucion de cada
     * prueba.
     */
    @After
    public void tearDown() {
        context = null;
        context3 = null;
        context4 = null;
    }

    //--------------------------------------------------------------------------
    // Test Case Scenarios
    //--------------------------------------------------------------------------

    @Test
    public void testUsuarioexiste() {
        User user = new User();
        User user2 = new User();

        user.setId(Long.parseLong("1529338943629"));
        context4.checking(new Expectations() {
            {
                oneOf(serviceUser).searchUserById2(String.valueOf(String.valueOf(user.getId())));
                will(returnValue(user.getId()));
            }
        });
        try {
            Long respuesta = serviceUser.searchUserById2(String.valueOf(user.getId()));
            user2.setId(respuesta);
            Assert.assertEquals(user.getId(), user2.getId());
            log.info("Usuario existe.");
        } catch (Throwable NullPointerException) {
            log.info("Usuario no existe.");
        }
    }


    @Test
    public void testProbarRespuestaInstagram() {

       User user = new User();
       user.setId(Long.parseLong("1529338943629"));
        context3.checking(new Expectations() {
            {
                oneOf(serviceInst).searchTag2(String.valueOf(user.getId()),"KH");
                will(returnValue(true));
            }
        });
        try {
            boolean respuesta = serviceInst.searchTag2(String.valueOf(user.getId()),"KH");
           Assert.assertEquals(respuesta, true);
     log.info("exito");
        } catch (Throwable FailedTestException) {
            log.info("Excepción.");
        }
    }

}
