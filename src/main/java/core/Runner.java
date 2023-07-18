package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class Runner {
	public static void psvm(String []args) {
		ClassLoader loader = Runner.class.getClassLoader();
		try {
			File file = new File(Objects.requireNonNull(loader.getResource("services/serviceAccountKey.json")).getFile());
			if(file != null) {
				FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
				FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

				FirebaseApp.initializeApp(options);
				SpringApplication.run(Runner.class, args);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo JSON não encontrado. Erro: " + e);
		} catch (IOException e) {
			System.out.println("Erro de credencial. Erro: " + e);
		} catch (NullPointerException e) {
			System.out.println("Erro de excessão. Erro: " + e);
		}
	}
}
