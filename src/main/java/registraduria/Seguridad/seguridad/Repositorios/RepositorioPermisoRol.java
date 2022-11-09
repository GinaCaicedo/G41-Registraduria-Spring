package registraduria.Seguridad.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.Seguridad.seguridad.Modelos.PermisoRol;

public interface RepositorioPermisoRol  extends MongoRepository<PermisoRol, String> {
    @Query("{'rol.$id': ObjectId(?0), 'permiso.$id': ObjectId(?1)}")
    public PermisoRol findByRolAndPermissions(String idRol, String idPermiso);
}
