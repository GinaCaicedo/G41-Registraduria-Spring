package registraduria.Seguridad.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import registraduria.Seguridad.seguridad.Modelos.Permiso;


public interface RepositorioPermiso extends MongoRepository<Permiso,String> {
    //metodo y url busco permiso
    @Query("{'url':?0,'metodo:?1'}")
    public Permiso findbyMethodandUrl(String url, String Metodo);

    public Permiso findByUrlAndMethod(String url, String metodo);
}
