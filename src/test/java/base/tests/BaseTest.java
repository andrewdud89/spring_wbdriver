package base.tests;

import base.Application;
import base.pages.SearchPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

    @Autowired
    SearchPage searchPage;

    @Test
    public void baseTest(){
        searchPage.openPage();
    }
}
