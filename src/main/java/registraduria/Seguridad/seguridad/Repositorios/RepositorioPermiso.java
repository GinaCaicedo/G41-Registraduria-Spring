package registraduria.Seguridad.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import registraduria.Seguridad.seguridad.Modelos.Permiso;


public interface RepositorioPermiso extends MongoRepository<Permiso,String> {
}
