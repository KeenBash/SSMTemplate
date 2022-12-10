import com.ssm.template.realm.MyRealm;
import com.ssm.template.service.UserService;
import com.ssm.template.util.RandomSalt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-mvc.xml"})
public class ShiroTest {


    @Test
    public void test1(@Autowired MyRealm myRealm) {
        String username = "admin";
        String password = "admin";
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(myRealm);

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException("用户名错误");
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException("密码错误");
        }
    }

    @Test
    public void test2() {
        // shiro加密测试
        String pwd = "admin";
        Md5Hash md5Hash = new Md5Hash(pwd, "salt", 3);
        System.out.println("md5Hash = " + md5Hash);
    }


    @Test
    public void test3(@Autowired UserService service) {
        // 存储账户
        String username = "aa";
        String password = "aa";
        String salt = "" + RandomSalt.numSalt();
        service.registerUser(username, password, salt);
    }


    @RepeatedTest(5)
    public void test4() {
        // 随机salt
        System.out.println("RandomSalt.numSalt() = " + RandomSalt.numSalt());
    }
}
