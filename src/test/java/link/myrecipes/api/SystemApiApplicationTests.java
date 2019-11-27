package link.myrecipes.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemApiApplicationTests {
    @Autowired
    DefaultListableBeanFactory beanFactory;

    @Test
    public void main_메소드_정상_확인() {
        SystemApiApplication.main(new String[]{"--server.port=9999"});
    }

    @Test
    public void 빈_리스트_조회() {
        String[] beans = beanFactory.getBeanDefinitionNames();

        Arrays.stream(beans)
                .sorted()
                .map(s -> s + "\t" + beanFactory.getBean(s).getClass().getName())
                .forEach(System.out::println);
    }
}
