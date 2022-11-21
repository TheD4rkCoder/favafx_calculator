module com.bx.fallmerayer.javafxtest.favafx_test {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.bx.fallmerayer.javafxtest.favafx_test to javafx.fxml;
    exports com.bx.fallmerayer.javafxtest.favafx_test;
}