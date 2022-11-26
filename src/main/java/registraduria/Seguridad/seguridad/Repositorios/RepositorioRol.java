package registraduria.Seguridad.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.Seguridad.seguridad.Modelos.PermisoRol;
import registraduria.Seguridad.seguridad.Modelos.Rol;

public interface RepositorioRol extends MongoRepository<Rol,String> {
    //@Query("{'rol.$id':ObjectId(?0),'permiso.$id':ObjectId(?1)}")
    //Rol findByRolAndPermissions(String idRol, String idPermiso);
}
