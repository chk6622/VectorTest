package com.vector;

import com.vector.Repository.IUserRepository;
import com.vector.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)
public class UserTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void shouldAddUser(){
        long beforeCount = this.userRepository.count();
        User u = this.userRepository.save(new User("test@email.com","12345","firstName","lastName"));
        long afterCount = this.userRepository.count();
        Assert.assertEquals(++beforeCount,afterCount);

        u = this.userRepository.save(new User("test1@email.com","111111","firstName1","lastName1"));
        afterCount = this.userRepository.count();

        Assert.assertEquals(++beforeCount,afterCount);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldGetAllUser(){
        long count = this.userRepository.count();
        List users = this.userRepository.findAll();
        Assert.assertEquals(count,users.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldGetAUserByEmail(){
        User user = this.userRepository.getOne("test@email.com");
        Assert.assertNotNull(user);
        Assert.assertEquals("12345",user.getPassword());
        Assert.assertEquals("firstName",user.getFirstName());
        Assert.assertEquals("lastName",user.getLastName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldUpdateUser(){
        User user = this.userRepository.save(new User("test2@email.com","11111","firstName2","lastName2"));
        Assert.assertNotNull(user);
        user.setPassword("22222");
        user.setFirstName("firstName3");
        user.setLastName("lastName3");
        this.userRepository.save(user);
        User updateUser = this.userRepository.getOne("test2@email.com");
        Assert.assertEquals(user.getPassword(),updateUser.getPassword());
        Assert.assertEquals(user.getFirstName(),updateUser.getFirstName());
        Assert.assertEquals(user.getLastName(),updateUser.getLastName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldDeleteUser(){
        User user = this.userRepository.save(new User("test3@email.com","11111","firstName2","lastName2"));
        Assert.assertNotNull(user);
        long beforeCount = this.userRepository.count();
        this.userRepository.delete(user);
        long afterCount = this.userRepository.count();
        Assert.assertEquals(beforeCount-1,afterCount);
        user = this.userRepository.findByEmail("test3@email.com");
        Assert.assertNull(user);
    }
}
