import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class crearPerfilController extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Labels y Campos de Texto
        Label lblCedula = new Label("Cédula Profesional:");
        TextField txtCedula = new TextField();

        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();

        Label lblEspecialidad = new Label("Especialidad:");
        ComboBox<String> cmbEspecialidad = new ComboBox<>();
        cmbEspecialidad.getItems().addAll("Cardiología", "Neurología", "Pediatría", "Otros");

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Label lblConfirmarContrasena = new Label("Confirmación de Contraseña:");
        PasswordField txtConfirmarContrasena = new PasswordField();

        // Botones
        Button btnCrearPerfil = new Button("Crear Perfil");
        Button btnRegresar = new Button("Regresar");

        // Evento para el botón "Crear Perfil"
        btnCrearPerfil.setOnAction(e -> mostrarVentanaCreado(primaryStage));

        // Evento para regresar a la ventana "ConsultaController"
        btnRegresar.setOnAction(e -> regresarAConsulta(primaryStage));

        // Layout de Formulario
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(lblCedula, 0, 0);
        grid.add(txtCedula, 1, 0);
        grid.add(lblNombre, 0, 1);
        grid.add(txtNombre, 1, 1);
        grid.add(lblEspecialidad, 0, 2);
        grid.add(cmbEspecialidad, 1, 2);
        grid.add(lblContrasena, 0, 3);
        grid.add(txtContrasena, 1, 3);
        grid.add(lblConfirmarContrasena, 0, 4);
        grid.add(txtConfirmarContrasena, 1, 4);

        HBox buttonBox = new HBox(15, btnCrearPerfil, btnRegresar);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, grid, buttonBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to right, #1E90FF, #87CEFA);");

        // Configurar la escena y mostrar la ventana
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Registro de Perfil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaCreado(Stage primaryStage) {
        Stage ventanaCreado = new Stage();
        Label lblMensaje = new Label("Perfil creado exitosamente.");
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setOnAction(e -> {
            ventanaCreado.close();
            regresarAConsulta(primaryStage);
        });

        VBox layout = new VBox(15, lblMensaje, btnAceptar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 150);
        ventanaCreado.setTitle("Perfil Creado");
        ventanaCreado.setScene(scene);
        ventanaCreado.show();
    }

    private void regresarAConsulta(Stage primaryStage) {
        // Aquí deberías cargar la escena de "ConsultaController"
        // Por ejemplo: new ConsultaController().start(primaryStage);
        primaryStage.setTitle("Consulta");
        primaryStage.setScene(new Scene(new Label("Pantalla de Consulta"), 500, 400)); // Simulación
    }

    public static void main(String[] args) {
        launch(args);
    }
}

