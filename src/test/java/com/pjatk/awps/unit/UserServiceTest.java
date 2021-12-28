package com.pjatk.awps.unit;

import com.pjatk.awps.model.AppUser;
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
        AppUser appUser = new AppUser("username");

        //WHEN
        userService.save(appUser);

        //THEN
        assertThat(appUser.getAddress() != null);
    }

    @Test
    void shouldCheckLoginBeforeRegister(){
        //GIVEN
        AppUser appUser = new AppUser("user", "password", "w@wp.pl");

        //WHEN
        userService.register(appUser);

        //THEN
        verify(userRepository).save(appUser);
    }

    @Test
    @Disabled
    void shouldRegister(){
        //GIVEN
        AppUser appUser = new AppUser("user", "password", "w@wp.pl");

        //WHEN
        userService.register(appUser);

        //THEN
        verify(userRepository).save(appUser);
    }

}
