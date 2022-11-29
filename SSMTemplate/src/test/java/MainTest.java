import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.template.entity.Student;
import com.ssm.template.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

// 引入spring context环境的测试类
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-mvc.xml"})
public class MainTest {

    @Autowired
    StudentService service;

    @Test
    public void test1() {
        // 测试分页
        PageHelper.startPage(1, 4);
        List<Student> students = service.getStudents();
        PageInfo<Student> info = new PageInfo<>(students);
        System.out.println(info);
    }

}
