package com.staxter.user;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.user.controller.UserController;
import com.staxter.user.model.User;
import com.staxter.user.repository.UserRepository;
import com.staxter.user.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class is responsible for load and provide all preloaded and needed objects and configuration
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InitializerTestClass {

    MockMvc mockMvc;

    @Autowired
    protected UserController userController;

    @Mock
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    static final String requestUrl="http://localhost:8081/userservice/register";

    User user;

    /**
     * before all tests load and run this method run
     * @throws Exception
     */
    @Before
    protected  void setUp() throws Exception {
        /**
         * For testing REST end point , we need controller to map our request. As we know our Controller load in Dispatcher Servlet
         * for mapping request. with this line of code we can building a DispatcherServlet( standalone DispatcherServlet)
         * and all needed infrastructure for register our controller in this Dispatcher. because of this method run before all tests
         *  ,we have a controller and can use this controller.
         */
        this.mockMvc = standaloneSetup(this.userController).build();
        /**
         * create a user initiated object and provide it for all unit tests , that need this object.
         */
        user=new User();
        user.setFirstName("Babak");
        user.setLastName("Mirghafari");
        user.setUserName("Mirghafari");
        user.setPlainTextPassword("Mirghafari");
    }

    /**
     * This method responsible for write user object to JSONObject. because of for test REST endpoint , we need JSONObject.
     * @param user
     * @return
     */
    protected  static String writeObjectAsJasonString(final User user) {

        ObjectMapper mapper=new ObjectMapper();
        try {

            mapper.disable(MapperFeature.USE_ANNOTATIONS);
            return mapper.writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
