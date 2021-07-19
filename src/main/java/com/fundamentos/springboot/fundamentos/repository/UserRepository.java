package com.fundamentos.springboot.fundamentos.repository;


import com.fundamentos.springboot.fundamentos.dto.UserDto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Ejemplos JPQL
    @Query("Select u from User u Where u.email=?1")
    Optional<User> findByUserEmail(String email);
    //Ejemplos JPQL

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Ejemplo QueryMethods
    List<User> findByName(String name);
    //Ejemplo QueryMethods
    Optional<List<User>> findByEmailAndName(String email,String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name,String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByIdDesc(String name);
    List<User> findByNameContainingOrderByIdDesc(String name);


    //JPQL con named parameters
    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto.UserDto(u.id, u.name, u.birthDate)" +
            " FROM User u "+
            " WHERE u.birthDate=:parametroFecha "+
            " and u.email=:parametroEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail")String email);






}
