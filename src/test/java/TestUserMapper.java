import com.netease.fimi.bhpj.BackenApplaction;
import com.netease.fimi.bhpj.domain.User;
import com.netease.fimi.bhpj.repository.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackenApplaction.class)
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAddUser() {
        User user1 = new User();
        user1.setUname("buyer");
        user1.setPassword("reyub");
        userMapper.addUser(user1);
        User user2 = new User();
        user2.setUname("seller");
        user2.setPassword("relles");
        userMapper.addUser(user2);
    }
}
