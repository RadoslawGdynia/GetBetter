module GetBetter {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.junit.jupiter.api;

    opens GetBetter;
    opens GetBetter.Kalendarz;
    opens GetBetter.DoZrobienia;
}