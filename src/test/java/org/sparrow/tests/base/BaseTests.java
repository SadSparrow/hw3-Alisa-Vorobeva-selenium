package org.sparrow.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.sparrow.framework.managers.DriverManager;
import org.sparrow.framework.managers.InitManager;
import org.sparrow.framework.managers.PageManager;
import org.sparrow.framework.managers.TestPropManager;

import static org.sparrow.framework.util.PropConst.BASE_URL;

public class BaseTests {

    protected PageManager app = PageManager.getPageManager();

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
