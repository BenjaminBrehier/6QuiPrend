module fr.benjaminbrehier._6quiprend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens fr.benjaminbrehier._6quiprend to javafx.fxml;
    exports fr.benjaminbrehier._6quiprend;
}