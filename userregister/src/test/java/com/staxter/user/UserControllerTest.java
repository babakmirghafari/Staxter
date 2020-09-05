package com.staxter.user;

import com.staxter.user.exception.CheckUserPropertyNullValueException;
import com.staxter.user.exception.UserAlreadyExistException;
import com.staxter.user.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends InitializerTestClass {

    @Override
    @Before
    public  void setUp() throws Exception {
        super.setUp();
    }

    /**
     * check if user send RequestObject in request body ,RestEndPoint should return 200
     * and if user don't send RequestObject in request body  ,RestEndPoint should return 400
     * @throws Exception
     */
    @Test
    public void testInsertNewUser() throws Exception{

        /**
         * check 200 response, Also after 200 response should return user object with value
         */
        mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectAsJasonString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("firstName").isNotEmpty())
                .andExpect(jsonPath("lastName").isNotEmpty())
                .andExpect(jsonPath("userName").isNotEmpty())
                .andExpect(jsonPath("plainTextPassword").doesNotExist())
                .andExpect(jsonPath("hashedPassword").doesNotExist());


    }

    @Test
    public void checkNullUserSent() throws Exception{
        /**
         * check 400 response
         */
        mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectAsJasonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test(expected = UserAlreadyExistException.class)
    public void checkException() throws Exception{
        if (userRepository.findUserByUserName(user.getUserName()).isPresent()){
            try {
                mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                        .content(writeObjectAsJasonString(user)))
                        .andExpect(status().isConflict());
            } catch (NestedServletException e){
                throw (UserAlreadyExistException) e.getCause();
            }
        }
    }

    @Test(expected = CheckUserPropertyNullValueException.class)
    public void checkNullValueForUser() throws Exception{
        User user=new User();
        try {
            mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
            .content(writeObjectAsJasonString(user)))
            .andExpect(status().isBadRequest());
        } catch (NestedServletException e){
            throw (CheckUserPropertyNullValueException)e.getCause();
        }
    }
}


