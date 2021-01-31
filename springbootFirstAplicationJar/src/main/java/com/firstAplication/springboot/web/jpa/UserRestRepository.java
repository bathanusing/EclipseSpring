package com.firstAplication.springboot.web.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Permite Exponer en la ruta /users el contenido de la tabla relacionada al
 * User.java Esto permite consultar, crear y borrar usuarios desde un servicio
 * Rest autoconfigurado
 * 
 * @author Flia Atacho Sanchez
 *
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByRole(@Param("role") String role);
}
