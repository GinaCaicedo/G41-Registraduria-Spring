package registraduria.Seguridad.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.Seguridad.seguridad.Modelos.Rol;

public interface RepositorioRol extends MongoRepository<Rol,String> {
}
