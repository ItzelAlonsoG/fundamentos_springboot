package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.POJO.UserPOJO;
import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.component.ComponentTwoImplemet;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	// se inyecta la inferdaz.

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private MyMessage myMessage;
	private UserRepository userRepository;


	private UserPOJO userPOJO;

	public FundamentosApplication(@Qualifier("componentTwoImplemet") ComponentDependency componentDependency, MyBean myBean,MyBeanWithDependency myBeanWithDependency,MyBeanWithProperties myBeanWithProperties,UserPOJO userPOJO,MyMessage myMessage,UserRepository userRepository){
		this.componentDependency=componentDependency;
		this.myBean= myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPOJO = userPOJO;
		LOGGER.error("Esto es un errro del aplicativo");
		this.myMessage = myMessage;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}


	//dependencia utilizada en una implementacion
	@Override
	public void run(String... args) throws Exception {
		//ClasesAnteriores();
		saveUsersInDataBase();
		grtInformationJpqlFormUser();
	}

	private void grtInformationJpqlFormUser(){
		/*LOGGER.info("User con el método findByUserEmail: " + userRepository.findByUserEmail("danteAlonso@hotmail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").ascending())
						.stream()
						.forEach(user -> LOGGER.info("Usuarios con el método sort= " + user));
		userRepository.findByName("Itzel").stream().forEach(user -> LOGGER.info("USUARIO CON QUERYMETHOD: " +user));

		LOGGER.info("Usuario encontrado por nombre y email: " + userRepository.findByEmailAndName("danteAlonso@hotmail.com","uribe")
				.orElseThrow(()-> new RuntimeException("USUARIO NO ENCONTRADO")));

		userRepository.findByNameLike("%I%").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike: " + user));

		userRepository.findByNameOrEmail(null,"user10@hotmail.com").stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail: " + user));

		userRepository.findByNameOrEmail("user10",null).stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail: " + user));
         */
		userRepository.findByBirthDateBetween(LocalDate.of(2014,3,1),LocalDate.of(2014,7,2)).stream()
			.forEach(user -> LOGGER.info("Uusuario con intervalo de fechas: "  + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
		.stream()
		.forEach(user -> LOGGER.info("Usuraio encontrado con like y ordenado "+ user));



	}

	private void saveUsersInDataBase(){
		User user1 = new User("Jhon","itzel@hotmail.com", LocalDate.of(2021,03,07));
		User user2= new User("Itzel","itzelAlonso@hotmail.com", LocalDate.of(1997,02,07));
		User user3= new User("uribe","danteAlonso@hotmail.com", LocalDate.of(2014,8,20));
		User user4= new User("user4","user4@hotmail.com", LocalDate.of(2014,9,20));
		User user5= new User("user5","user5@hotmail.com", LocalDate.of(2014,12,20));
		User user6= new User("user6","user6@hotmail.com", LocalDate.of(2014,10,20));
		User user7= new User("user7","user7@hotmail.com", LocalDate.of(2014,05,20));
		User user8= new User("user8","user8@hotmail.com", LocalDate.of(2014,06,20));
		User user9= new User("user9","user9@hotmail.com", LocalDate.of(2014,03,20));
		User user10= new User("user10","user10@hotmail.com", LocalDate.of(2014,07,20));
		User user11= new User("user11","user11@hotmail.com", LocalDate.of(2014,9,20));

		List<User> list = Arrays.asList(user1, user2,user3 ,user4 ,user5 ,user6 ,user7 ,user8 ,user9 ,user10 ,user11);
		list.stream().forEach(userRepository::save);
	}


	private void ClasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPOJO.getEmail() + " " + userPOJO.getPassword() + " EDAD: " + userPOJO.getAge());
		try {
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		}catch(Exception e){
			LOGGER.error("ESTO ES UN ERROR AL DIVIDIR POR CERO " + e.getStackTrace());

		}
		myMessage.fax();
	}
}
