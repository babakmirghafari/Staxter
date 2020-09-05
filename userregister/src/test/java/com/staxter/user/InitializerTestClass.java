package com.staxter.user;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.user.controller.UserController;
import com.staxter.user.model.User;
import com.staxter.user.repository.UserRepository;
import com.staxter.user.service.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

    @Before
    protected  void setUp() throws Exception {
        this.mockMvc = standaloneSetup(this.userController).build();
        user=new User();
        user.setFirstName("Babak");
        user.setLastName("Mirghafari");
        user.setUserName("Mirghafari");
        user.setPlainTextPassword("Mirghafari");
    }

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
