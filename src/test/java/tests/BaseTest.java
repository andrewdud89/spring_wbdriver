package tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import core.pages.SearchPage;

@Component
public class BaseTest {

    @Autowired
    SearchPage searchPage;

    @Test
    public void baseTest(){
        searchPage.openPage();
    }
}
