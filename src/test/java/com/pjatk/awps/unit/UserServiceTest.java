package com.pjatk.awps.unit;

import com.pjatk.awps.model.Person;
import com.pjatk.awps.repository.UserRepository;
import com.pjatk.awps.service.AddressService;
import com.pjatk.awps.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private final UserRepository userRepository = mock(UserRepository.class);
    @Mock
    private final AddressService addressService = mock(AddressService.class);

    private final UserService userService = new UserService(userRepository, addressService);

    @Test
    void shouldCreateAddress(){
        //GIVEN
        Person person = new Person("username");

        //WHEN
        userService.save(person);

        //THEN
        assertThat(person.getAddress() != null);
    }

    @Test
    void shouldCheckLoginBeforeRegister(){
        //GIVEN
        Person person = new Person("user", "password");

        //WHEN
        userService.register(person);

        //THEN
        verify(userRepository).save(person);
    }

    @Test
    @Disabled
    void shouldRegister(){
        //GIVEN
        Person person = new Person("user", "password");

        //WHEN
        userService.register(person);

        //THEN
        verify(userRepository).save(person);
    }

}
