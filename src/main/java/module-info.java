module com.bx.fallmerayer.javafxtest.favafx_test {
    requires javafx.controls;
    requires javafx.fxml;
    requires MathParser.org.mXparser;


    opens com.bx.fallmerayer.javafxtest.favafx_test to javafx.fxml;
    exports com.bx.fallmerayer.javafxtest.favafx_test;
}