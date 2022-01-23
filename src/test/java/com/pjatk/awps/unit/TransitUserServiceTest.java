package com.pjatk.awps.unit;

import com.pjatk.awps.model.Person;
import com.pjatk.awps.model.Transit;
import com.pjatk.awps.model.TransitUser;
import com.pjatk.awps.model.enums.Role;
import com.pjatk.awps.service.TransitUserService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransitUserServiceTest {
    private final TransitUserService transitUserService = new TransitUserService(null);

//    @Test
//    void shouldMakeTransitUser() {
//        //GIVEN
//        Person person = new Person("username");
//        Transit transit = new Transit();
//
//        //WHEN
//        TransitUser transitUser = transitUserService.make(transit, person, Role.None);
//
//        //THEN
//        assertThat(transitUser.getPersonId()!=null);
//    }

}
