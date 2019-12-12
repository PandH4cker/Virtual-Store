package store.business.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        ClientTest.class,
        CharacterNameTest.class,
        BookTest.class
})
public class VirtualStoreTestSuite {
}