package com.java.desktopApp;


//import org.springframework.boot.SpringApplication;
import com.java.desktopApp.config.ContextManager;
import com.java.desktopApp.config.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

	private ViewManager viewManager;
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		//pasamos el contexto
		ContextManager contexto=ContextManager.getContexto();
		context = SpringApplication.run(Main.class);
		contexto.setContext(context);

		ViewManager vista=ViewManager.loadVista();
		//pasamos el stage
		vista.loadStage(stage);
		vista.getStage().setTitle("APP DESKTOP");
		//cargamos el fxml
		vista.loadfxml("/views/main.fxml");
		vista.addLoaderContext();
		vista.showView();

	}


}
