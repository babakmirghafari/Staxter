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
     * check HTTP_CODE 200 response, Also after 200 response should return user object with value
     */
    @Test
    public void testInsertNewUser() throws Exception{
        /**
         * send request to controller with expected object in request Body
         */
        mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectAsJasonString(user)))
                /**
                 * Check HTTP_CODE 200
                 */
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("firstName").isNotEmpty())
                .andExpect(jsonPath("lastName").isNotEmpty())
                .andExpect(jsonPath("userName").isNotEmpty())
                /**
                 * this properties should be absent in return JSONObject
                 */
                .andExpect(jsonPath("plainTextPassword").doesNotExist())
                .andExpect(jsonPath("hashedPassword").doesNotExist());


    }

    /**
     * check HTTP_ERROR_CODE 400
     */
    @Test
    public void checkNullUserSent() throws Exception{

        mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                .content(writeObjectAsJasonString(null)))
                /**
                 * Check HTTP_ERROR_CODE 400
                 */
                .andExpect(status().isBadRequest());
    }

    /**
     * Check {@link UserAlreadyExistException} and HTTP_ERROR_CODE 409
     * because of this method return {@link UserAlreadyExistException} , this exception define as test parameter
     * @throws Exception
     */
    @Test(expected = UserAlreadyExistException.class)
    public void checkException() throws Exception{
        if (userRepository.findUserByUserName(user.getUserName()).isPresent()){
            try {
                mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
                        .content(writeObjectAsJasonString(user)))
                        /**
                         * Check HTTP_ERROR_CODE 409
                         */
                        .andExpect(status().isConflict());
                /**
                 * Because of {@link UserAlreadyExistException} extend from {@link RuntimeException}, when method face to this exception
                 * finally  throw {@link NestedServletException}. because of that , this method face to {@link NestedServletException}
                 * instead of {@link UserAlreadyExistException}. for avoid this error , we can catch {@link NestedServletException}
                 * and cast exception cause for our desired exception.
                 */
            } catch (NestedServletException e){
                /**
                 * cast exception cause to {@link UserAlreadyExistException}
                 */
                throw (UserAlreadyExistException) e.getCause();
            }
        }
    }

    /**
     * Check {@link CheckUserPropertyNullValueException} and HTTP_ERROR_CODE 400
     * because of this method return {@link CheckUserPropertyNullValueException} , this exception define as test parameter
     * @throws Exception
     */
    @Test(expected = CheckUserPropertyNullValueException.class)
    public void checkNullValueForUser() throws Exception{
        User user=new User();
        try {
            mockMvc.perform(post(requestUrl).contentType(MediaType.APPLICATION_JSON)
            .content(writeObjectAsJasonString(user)))
                    /**
                     * Check HTTP_ERROR_CODE 400
                     */
            .andExpect(status().isBadRequest());
        } catch (NestedServletException e){
            /**
             * cast exception cause to {@link CheckUserPropertyNullValueException}
             */
            throw (CheckUserPropertyNullValueException)e.getCause();
        }
    }
}


